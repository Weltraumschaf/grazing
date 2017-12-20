package de.weltraumschaf.grazing;

import de.weltraumschaf.commons.system.ExitCode;

public enum ExitCodes implements ExitCode {
    OK(0), FATAL(255);

    private final int code;

    ExitCodes(final int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
