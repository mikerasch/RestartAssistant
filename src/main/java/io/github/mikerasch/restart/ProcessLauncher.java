package io.github.mikerasch.restart;

import java.io.IOException;

public class ProcessLauncher {
	private final String executablePath;

	public ProcessLauncher(String executablePath) {
		this.executablePath = executablePath;
	}

	public void launch() {
		try {
			new ProcessBuilder(executablePath)
					.start();
		} catch (IOException e) {

		}
	}
}
