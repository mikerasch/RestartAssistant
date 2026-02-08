package io.github.mikerasch.restart;

public record ApplicationArguments(long pid, String executablePath) {
    private enum Arguments {
        PID("--pid"),
        EXECUTABLE_PATH("--executablePath");

        private final String flag;

        Arguments(String flag) {
            this.flag = flag;
        }

        public String getFlag() {
            return flag;
        }
    }

    public static ApplicationArguments from(String[] args) {
        validateArgs(args);

        Long pid = null;
        String executablePath = null;

        for (String arg : args) {
            if (arg.startsWith(Arguments.PID.getFlag() + "=")) {
                pid = parsePID(arg);
            } else if (arg.startsWith(Arguments.EXECUTABLE_PATH.getFlag() + "=")) {
                executablePath = arg.substring((Arguments.EXECUTABLE_PATH.getFlag() + "=").length());
            }
        }

        if (pid == null) {
            throw InvalidArgument.missing(Arguments.PID.getFlag());
        }
        if (executablePath == null) {
            throw InvalidArgument.missing(Arguments.EXECUTABLE_PATH.getFlag());
        }

        return new ApplicationArguments(pid, executablePath);
    }

    private static Long parsePID(String arg) {
        final String value = arg.substring((Arguments.PID.getFlag() + "=").length());
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw InvalidArgument.valueError(Arguments.PID.getFlag());
        }
    }

    private static void validateArgs(String[] args) {
        if (args.length == 0) {
            throw InvalidArgument.empty();
        }
    }
}
