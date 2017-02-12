package com.mpk.services;

import com.mpk.dao.BusStopTimesRepository;
import com.mpk.entity.BusStopTimes;
import com.mpk.services.timetable.TimetableAtBusStopFlyweight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class TimeHolderImpl extends Observable implements TimeHolder {

    private ReadWriteLock lock;
    private BusStopTimesRepository busStopTimesRepository;
    private List<BusStopTimes> times;


    @Autowired
    public TimeHolderImpl(BusStopTimesRepository busStopTimesRepository) {
        this.busStopTimesRepository = busStopTimesRepository;
        this.times = new ArrayList<>();
        this.lock = new ReentrantReadWriteLock();
        this.initialize();
    }

    @Override
    @Transactional
    public void setTimeDiff(long busOneId, long busTwoId, int timeDiff) {
        lock.writeLock().lock();
        BusStopTimes time = findByIds(busOneId, busTwoId);
        if (busOneId > busTwoId) {
            long temp = busOneId;
            busOneId = busTwoId;
            busTwoId = temp;
        }
        if(time == null){
            time = new BusStopTimes(busOneId, busTwoId, timeDiff);
            busStopTimesRepository.save(time);
            times.add(time);
        }else{
            time.setTime(timeDiff);
            busStopTimesRepository.save(time);
        }
        lock.writeLock().unlock();
        this.notifyObservers();
    }

    @Override
    public int getTimeDiff(long busOneId, long busTwoId) {
        lock.readLock().lock();
        int time = this.findByIds(busOneId, busTwoId).getTime();
        lock.readLock().unlock();
        return time;
    }

    @Override
    @Transactional
    public void deleteTimeDiff(long busOneId, long busTwoId) {
        lock.writeLock().lock();
        BusStopTimes time = this.findByIds(busOneId, busTwoId);
        this.busStopTimesRepository.delete(time.getId());
        this.times.remove(time);
        lock.writeLock().unlock();
        this.notifyObservers();
    }

    private void initialize(){
        this.times = this.busStopTimesRepository.findAll();
    }

    private BusStopTimes findByIds(long busOneId, long busTwoId){
        if (busOneId > busTwoId) {
            long temp = busOneId;
            busOneId = busTwoId;
            busTwoId = temp;
        }
        for (BusStopTimes b: this.times) {
            if (b.getBusOneId() == busOneId && b.getBusTwoId() == busTwoId) {
                return b;
            }
        }
        return null;
    }
}
