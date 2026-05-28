package Notebook;

import java.io.*;
import java.util.ArrayList;

public class Database{
    public ArrayList<UserInformation> userData=new ArrayList<>();
    public String lastLoginName="请输入用户名";
    public String lastLoginPassword="";
    public boolean isSelectedAgreement;
    public boolean isSelectedRemember;
    private static final String DATA_LOCATION="userData.dat";

    //加入默认测试账号
    public Database()
    {
        addUser("admin","123456");
    }

    public void addUser(String userName,String password)
    {
        UserInformation newUser=new UserInformation(userName,password);
        userData.add(newUser);
    }

    public UserInformation loginCheck(String userName,String password)
    {
        for(UserInformation inform:userData){
            if(inform.userName.equals(userName)) {
                if (inform.password.equals(password)) {
                    return inform;
                }
            }
        }
        return null;
    }

    public void saveFileToDisk()
    {
        try(ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(DATA_LOCATION))){
            oos.writeObject(this.userData);
            oos.writeObject(this.lastLoginName);
            oos.writeObject(this.lastLoginPassword);
            oos.writeObject(this.isSelectedAgreement);
            oos.writeObject(this.isSelectedRemember);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadFileToProgram()
    {
        File file=new File(DATA_LOCATION);
        if(file.exists()){
            try(ObjectInputStream ois =new ObjectInputStream(new FileInputStream(DATA_LOCATION))){
                this.userData=(ArrayList<UserInformation>)ois.readObject();
                this.lastLoginName=(String)ois.readObject();
                this.lastLoginPassword=(String)ois.readObject();
                this.isSelectedAgreement=(boolean)ois.readObject();
                this.isSelectedRemember=(boolean)ois.readObject();
            }catch(IOException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    public void setLastLoginInformation(String name,String password,boolean agreement,boolean rememberPassword)
    {
        lastLoginName=name;
        if(rememberPassword){
            lastLoginPassword=password;
        }else {
            lastLoginPassword="请输入密码";
        }
        isSelectedAgreement=agreement;
        isSelectedRemember=rememberPassword;
    }
}
