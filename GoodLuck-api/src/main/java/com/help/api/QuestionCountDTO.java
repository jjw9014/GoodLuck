package com.help.api;

import java.io.Serializable;

public class QuestionCountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int newAddCount;

    private int unresolvedCount;

    private int resolvedCount;

    public int getNewAddCount() {
        return newAddCount;
    }

    public void setNewAddCount(int newAddCount) {
        this.newAddCount = newAddCount;
    }

    public int getUnresolvedCount() {
        return unresolvedCount;
    }

    public void setUnresolvedCount(int unresolvedCount) {
        this.unresolvedCount = unresolvedCount;
    }

    public int getResolvedCount() {
        return resolvedCount;
    }

    public void setResolvedCount(int resolvedCount) {
        this.resolvedCount = resolvedCount;
    }
}
