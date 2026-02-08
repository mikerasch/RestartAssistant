package io.github.mikerasch.restart;

import java.time.Duration;
import java.util.Optional;

public class ProcessTerminator {
	private final long pid;
	private static final int MAX_RETRIES = 5;
	private static final Duration RETRY_DELAY = Duration.ofSeconds(1);

	public ProcessTerminator(long pid) {
		this.pid = pid;
	}

	public void terminate() {
		final Optional<ProcessHandle> handleOpt = ProcessHandle.of(pid);

		if (handleOpt.isEmpty()) {
			return;
		}

		final ProcessHandle handle = handleOpt.get();

		handle.destroy();

		verifyTermination(handle);
	}

	private void verifyTermination(ProcessHandle handle) {
		int attempts = 0;
		while (attempts < MAX_RETRIES) {
			if (!handle.isAlive()) {
				break;
			}

			sleep();

			attempts++;
		}
	}

	private static void sleep() {
		try {
			Thread.sleep(RETRY_DELAY);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
