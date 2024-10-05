package Glufy.Pulse.utils.server;

public interface IServerFinderDoneListener {
    void onServerDone(ServerPinger pinger);

    void onServerFailed(ServerPinger pinger);
}
