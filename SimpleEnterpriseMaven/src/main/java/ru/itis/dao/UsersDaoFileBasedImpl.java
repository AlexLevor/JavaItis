package ru.itis.dao;

import com.google.common.base.Splitter;
import ru.itis.models.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersDaoFileBasedImpl implements UsersDao {

    private BufferedReader fileReader;
    private String fileName;

    public UsersDaoFileBasedImpl(String fileName) {
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
            this.fileName = fileName;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            String currentLine = fileReader.readLine();
            while (currentLine != null) {
                User currentUser = parseStringAsUser(currentLine);
                result.add(currentUser);
                currentLine = fileReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("SomeError");
        }
        return result;
    }

    @Override
    public User get(int userId) {
        String userIdString = Integer.toString(userId);
        try {
            String currentLine = fileReader.readLine();
            while (currentLine != null) {
                if(currentLine.startsWith(userIdString)) {
                    User currentUser = parseStringAsUser(currentLine);
                    return currentUser;
                }else{
                    currentLine = fileReader.readLine();
                }
            } throw new IllegalArgumentException();
        } catch (IOException e) {
            System.out.println("SomeError");
        }

        return null;
    }

    @Override
    public void save(User user) {
        try {
            FileWriter writer = new FileWriter(this.fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(user.getId() + " "
                    + user.getName() + " "
                    + user.getPassword() + " "
                    + user.getAge() );
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(int userId) {

    }

    private User parseStringAsUser(String line) {
        Splitter splitter = Splitter.on(" ");

        List<String> lines = splitter.splitToList(line);
        int id =Integer.parseInt(lines.get(0));
        String name = lines.get(1);
        String password = lines.get(2);
        int age = Integer.parseInt(lines.get(3));

        return new User(id, name, password, age);
    }
}
