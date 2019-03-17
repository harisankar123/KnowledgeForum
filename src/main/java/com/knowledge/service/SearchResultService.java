package com.knowledge.service;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;

import com.knowledge.dto.SearchCritera;
import com.knowledge.dto.SearchResult;

@Service
public class SearchResultService {
private static final String LOCAL_DNS = "192.168.0.1";
private static final String GOOGLE_DNS = "8.8.8.8";
private static final String RURAL = "rural";
private static final String MEASURE = "MEASURE";
private static final String COMMAND = "cmd";
private static final String SEPARATOR = " ";
private static final String MEASURE_COMMAND = "(Measure-Command {Resolve-DnsName -server %s %s}).totalseconds";
	public SearchResult getSearchResult(SearchCritera criteria) {
		Process p;
		try {
			p = Runtime.getRuntime().exec(COMMAND);
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			stdin.println(getCommandToExecute(criteria));
			stdin.close();
			p.waitFor();
			return getSearchResult(readProcessResult(p), criteria);

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
		return builder.append(criteria.getSearchCategory()).append(SEPARATOR).append(criteria.getUrl()).toString();
	}
	
	private String readProcessResult(Process p) throws IOException {
		final byte[] buffer = new byte[1024];
		final StringBuilder builder = new StringBuilder();
		for (int length = 0; (length = p.getInputStream().read(buffer)) != -1; )
		{
			builder.append(new String(buffer)).append("\n");
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		SearchResultService calc = new SearchResultService();
		SearchCritera criteria = new SearchCritera();
		criteria.setLocationCategory("urban");
		criteria.setSearchCategory("ping");
		criteria.setUrl("iter.ac.in");
		System.out.println(calc.getCommandToExecute(criteria));
	}
	
}
