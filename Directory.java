import java.util.ArrayList;
import java.util.Date;

class Directory{
    String directoryName;
    String timestamp;
    ArrayList<Directory> subDirectories;
    Directory parent;
    Directory(String directoryName,Directory parent){
        this.directoryName = directoryName;
        this.timestamp = new Date().toString();
        this.subDirectories = new ArrayList<>();
        this.parent = parent;

    }
}