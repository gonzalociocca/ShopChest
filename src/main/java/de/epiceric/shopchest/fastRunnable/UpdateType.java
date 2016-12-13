package de.epiceric.shopchest.fastRunnable;

/**
 * Created by ciocca on 11/12/16.
 */
public enum UpdateType
{
    MIN1(60000L),
    SLOW(325L),
    FAST(100L);

    private final long _time;
    private long _last;
    private long _timeSpent;
    private long _timeCount;

    private UpdateType(long time) { _time = time;
        _last = System.currentTimeMillis();
    }

    public boolean Elapsed()
    {
        if (UtilTime.elapsed(_last, _time))
        {
            _last = System.currentTimeMillis();
            return true;
        }

        return false;
    }

    public void StartTime()
    {
        _timeCount = System.currentTimeMillis();
    }

    public void StopTime()
    {
        _timeSpent += System.currentTimeMillis() - _timeCount;
    }

    public void PrintAndResetTime()
    {
        System.out.println(name() + " in a second: " + _timeSpent);
        _timeSpent = 0L;
    }
}