package com.mpk.services;


public interface TimeHolder {

    void setTimeDiff(long busOneId, long busTwoId, int timeDiff);
    int getTimeDiff(long busOneId, long busTwoId);
    void deleteTimeDiff(long busOneId, long busTwoId);
}
