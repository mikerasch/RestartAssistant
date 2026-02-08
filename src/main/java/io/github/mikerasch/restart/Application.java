package io.github.mikerasch.restart;

public class Application {
    public static void main(String[] args) {
        final ApplicationArguments applicationArguments = ApplicationArguments.from(args);

        final ProcessTerminator processTerminator = new ProcessTerminator(applicationArguments.pid());
        processTerminator.terminate();

        final ProcessLauncher processLauncher = new ProcessLauncher(applicationArguments.executablePath());
        processLauncher.launch();
    }
}
