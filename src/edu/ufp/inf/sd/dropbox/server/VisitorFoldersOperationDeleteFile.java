package edu.ufp.inf.sd.dropbox.server;

import edu.ufp.inf.sd.dropbox.client.ObserverImpl;

import java.io.Serializable;
import java.rmi.RemoteException;

public class VisitorFoldersOperationDeleteFile implements VisitorFoldersOperationsI, Serializable {
    public String fileToDelete;


    // Uses RMI-default sockets-based transport
    // Runs forever (do not passivates) - Do not needs rmid (activation deamon)
    // Constructor must throw RemoteException due to export()
    public VisitorFoldersOperationDeleteFile(String file) throws RemoteException {
        // Invokes UnicastRemoteObject constructor which exports remote object
        this.fileToDelete=file;
    }

    /**
     *  usamos o singleton para efetuar a operação que queremos
     * @param e
     * @return operação pretendida
     */
    @Override
    public Object visitConcreteElementUsers(DropBoxSubjectRI e) {
        SingletonFolderOperationsUser s = null;
        if (e instanceof DropBoxSubjectImpl) {
            s = ((DropBoxSubjectImpl) e).getSingletonStateFolder();
        } else if (e instanceof ObserverImpl) {
            s = ((ObserverImpl) e).getSingletonStateFolder();
        }
        return s.deleteFile(fileToDelete);
    }
}
