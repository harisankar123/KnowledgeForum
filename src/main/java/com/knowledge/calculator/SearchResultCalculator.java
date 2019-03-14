package com.knowledge.calculator;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;

import com.knowledge.dto.SearchCritera;

@Service
public class SearchResultCalculator {

	public String getSearchResult(SearchCritera criteria) {
		Process p;
		try {
			p = Runtime.getRuntime().exec("cmd");
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			stdin.println("ipconfig/all");
			stdin.close();
			p.waitFor();
			return readProcessResult(p);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Command Execution failed");
		}
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

}
