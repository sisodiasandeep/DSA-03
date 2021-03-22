import java.util.ArrayList;

public class VirtualCmd
{

    private final Directory root;
    private Directory currentDirectory;
    private final ArrayList<String> currentPath;

    public VirtualCmd()
    {
        root = new Directory("\\", null);
        currentDirectory = root;
        currentPath = new ArrayList<>();
    }

    void run(String command, String data)
    {
        if ("mkdir".equalsIgnoreCase(command))
        {
            mkdir(data);
        } else if ("ls".equalsIgnoreCase(command))
        {
            list();
        } else if ("tree".equalsIgnoreCase(command))
        {
            Directory tempDir = currentDirectory;
            tree(tempDir, 0);
        } else if ("cd".equalsIgnoreCase(command))
        {
            changeDir(data);
        } else if ("bk".equalsIgnoreCase(command))
        {
            back();
        } else if ("find".equalsIgnoreCase(command))
        {
            for (String path : find(data, currentDirectory, new ArrayList<>(), ".\\"))
            {
                System.out.println(path);
            }
        } else if ("exit".equalsIgnoreCase(command))
        {
            System.exit(0);
        } else
        {
            System.out.println("'" + command +
                    "' is not recognized as an internal or external command,operable program or batch file.");
        }
    }

    void list()
    {
        for (Directory directory : currentDirectory.subDirectories)
        {
            System.out.println(directory.timestamp + "  " + directory.directoryName);
        }
    }

    void mkdir(String folderName)
    {
        if (folderName == null)
        {
            System.out.println("The syntax of command is incorrect");
            return;
        }
        Directory newDirectory = new Directory(folderName, currentDirectory);
        if (isPresent(folderName, currentDirectory))
        {
            System.out.println(folderName + "is already exist in the directory");
            return;
        }
        if (currentDirectory == root)
        {
            root.subDirectories.add(newDirectory);
        } else
        {
            currentDirectory.subDirectories.add(newDirectory);
        }
    }

    private boolean isPresent(String folderName, Directory currentDirectory)
    {
        for (Directory dir : currentDirectory.subDirectories)
        {
            if (dir.directoryName.compareTo(folderName) == 0)
            {
                return true;
            }
        }
        return false;
    }

    ArrayList<String> find(String folderName, Directory currentDirectory,
                           ArrayList<String> path, String currentPath)
    {
        if (currentDirectory.directoryName.contains(folderName)) path.add(currentPath);
        for (Directory dir : currentDirectory.subDirectories)
        {
            find(folderName, dir, path, currentPath + dir.directoryName + "\\");
        }
        return path;
    }

    void printSpace(int level)
    {
        if (level != 0)
            System.out.print("\u2502");
        for (int space = level; space > 0; space--)
        {
            System.out.print("\t");

        }
    }

    void tree(Directory dir, int level)
    {
        for (Directory subDir : dir.subDirectories)
        {
            printSpace(level);
            System.out.println("\u2502");
            printSpace(level);
            System.out.println("\u2514" + subDir.directoryName);
            if (subDir.subDirectories.size() > 0)
            {
                tree(subDir, level + 1);
            }

        }
    }

    void changeDir(String folderName)
    {
        for (Directory directory : currentDirectory.subDirectories)
        {
            if (directory.directoryName.compareTo(folderName) == 0)
            {
                currentPath.add(directory.directoryName);
                currentDirectory = directory;
                return;
            }
        }
        System.out.println("The system cannot find the path specified.");
    }

    void back()
    {
        if (currentPath.size() > 0)
        {
            currentDirectory = currentDirectory.parent;
            currentPath.remove(currentPath.size() - 1);
        }

    }

    String getPath()
    {
        String path = root.directoryName;
        for (String dirName : currentPath)
        {
            if (dirName.compareTo("\\") == 0 || path.compareTo("\\") == 0)
            {
                path += dirName;
            } else
            {
                path += "\\" + dirName;
            }
        }
        return path;
    }
}