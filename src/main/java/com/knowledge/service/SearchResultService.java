package com.knowledge.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.knowledge.dto.SearchCritera;
import com.knowledge.dto.SearchResult;

@Service
public class SearchResultService {
	private static final String IPCONFIG = "ipconfig";
	private static final String LOCAL_DNS = "192.168.1.106";
	private static final String GOOGLE_DNS = "113.193.8.18";
	private static final String RURAL = "rural";
	private static final String MEASURE = "measure";
	private static final String DISPLAY_DNS = "displaydns";
	private static final String FLUSH_DNS = "flushdns";
	private static final String COMMAND = "cmd";
	private static final String SEPARATOR = " ";
	private static final String MEASURE_COMMAND = "(Measure-Command {Resolve-DnsName -server %s %s}).totalseconds";
	public SearchResult getSearchResult(SearchCritera criteria) {
		Process p;
		try {
			p = Runtime.getRuntime().exec(COMMAND);
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			String outFileName = "temp.txt";
			if(criteria.getLocationCategory().equalsIgnoreCase("rural")) {
				outFileName ="temp1.txt";
				stdin.println("F:/software/clumsy/clumsy.exe --filter outbound --lag on --lag-time 200");
			}
			String cmdToExecute = getCommandToExecute(criteria);
			System.out.println(cmdToExecute);
			if(criteria.getSearchCategory().equals(MEASURE)){
				stdin.println("powershell");
				stdin.println(cmdToExecute);
				cleanup(stdin);
				stdin.close();
				p.waitFor();
				return getSearchResult(readProcessResult(p), criteria);
			}
			else{
				stdin.println(cmdToExecute +" >"+outFileName);
				cleanup(stdin);
				stdin.close();
				p.waitFor();
				return getSearchResult(new String(Files.
						readAllBytes(Paths.get(outFileName))), criteria);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Command Execution failed");
		}
	}

	private SearchResult getSearchResult(String result, SearchCritera criteria) {
		return new SearchResult(criteria.getUrl(), criteria.getLocationCategory(), 
				criteria.getSearchCategory(), result.getBytes());
	}

	private String getCommandToExecute(SearchCritera criteria) {
		final StringBuilder builder = new StringBuilder();
		if (criteria.getSearchCategory().equals(MEASURE)) {
			return criteria.getLocationCategory().equals(RURAL)
					? String.format(MEASURE_COMMAND, GOOGLE_DNS, criteria.getUrl())
							: String.format(MEASURE_COMMAND, LOCAL_DNS, criteria.getUrl());
		}
		else if (criteria.getSearchCategory().equals(FLUSH_DNS) ||
				criteria.getSearchCategory().equals(DISPLAY_DNS)) {
			return IPCONFIG + SEPARATOR + criteria.getSearchCategory();
		}
		return builder.append(criteria.getSearchCategory()).append(SEPARATOR).append(criteria.getUrl()).toString();
	}
	
	private String readProcessResult(Process p) throws IOException {
		final byte[] buffer = new byte[1024*1024];
		final StringBuilder builder = new StringBuilder();
		for (int length = 0; (length = p.getInputStream().read(buffer)) != -1; )
		{
			builder.append(new String(buffer)).append("\n");
		}
		return builder.toString();
	}
	
	private void cleanup(PrintWriter stdin) {
		stdin.println("TASKKILL /IM clumsy.exe");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SearchResultService calc = new SearchResultService();
		SearchCritera criteria = new SearchCritera();
		criteria.setLocationCategory("urban");
		criteria.setSearchCategory("dig");
		criteria.setUrl("google.co.in");
		System.out.println(new String(calc.getSearchResult(criteria).getSearchResult()));
	}

}
