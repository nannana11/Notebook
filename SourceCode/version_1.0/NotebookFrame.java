package Notebook;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.BoxLayout.Y_AXIS;

public class NotebookFrame extends JFrame {

    //数据加载
    public UserInformation userInformation;
    public Note currentNote;
    TimeGetter timeGetter=new TimeGetter();
    Database database;

    //UI组件
    JPanel root=new JPanel();
    JPanel upField=new JPanel();
    JPanel centerField=new JPanel();
    JPanel downField=new JPanel();

    //左部滚轮区（展示已有的笔记）
    DefaultListModel<Note> listModel=new DefaultListModel<Note>();
    JList<Note> noteList=new JList<Note>(listModel);
    JScrollPane scrollList=new JScrollPane(noteList);
    //右部编辑区域
    JLabel titleShowLabel =new JLabel();
    JLabel contentShowLabel=new JLabel();
    JTextArea contentArea=new JTextArea();
    JScrollPane contentScroll=new JScrollPane(contentArea);
    JTextField titleField=new JTextField(30);
    //顶部功能键
    JButton saveButton=new JButton("保存");
    JButton newNoteButton=new JButton("创建");
    JButton deleteButton=new JButton("删除");
    JTextField searchField=new JTextField(30);
    JButton searchButton=new JButton("查询");
    //底部
    JLabel timeShowLabel=new JLabel();
    JLabel timeLabel=new JLabel();
    JLabel countShowLabel=new JLabel();
    JLabel countLabel=new JLabel();

    public NotebookFrame(String title,UserInformation userInformation,Database database)
    {
        super(title);
        //加载数据
        //获取到当前用户的信息
        this.userInformation=userInformation;
        this.database=database;

        //UI
        //根容器
        this.add(root);
        root.setLayout(new BorderLayout());
        //顶部功能区
        upField.setLayout(new FlowLayout(FlowLayout.LEFT));
        upField.add(saveButton);
        upField.add(newNoteButton);
        upField.add(deleteButton);
        upField.add(searchField);
        upField.add(searchButton);
        root.add(upField,BorderLayout.NORTH);
        //中部区域（左侧列表，右侧编辑区）
        root.add(centerField);
        centerField.setLayout(new BorderLayout());
        //右部编辑区
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JPanel rightField=new JPanel();
        rightField.setLayout(new BorderLayout());
        centerField.add(rightField,BorderLayout.CENTER);
        JPanel rightTopField=new JPanel();
        rightTopField.setLayout(new BoxLayout(rightTopField,BoxLayout.Y_AXIS));
        titleShowLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleField.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentShowLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightTopField.add(titleShowLabel);
        rightTopField.add(titleField);
        rightTopField.add(contentShowLabel);
        rightField.add(rightTopField,BorderLayout.NORTH);
        rightField.add(contentScroll,BorderLayout.CENTER);
        titleField.setPreferredSize(new Dimension(600,30));
        titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE,30));
        //左部选择区
        noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollList.setPreferredSize(new Dimension(200,0));
        centerField.add(scrollList,BorderLayout.WEST);
        root.add(centerField,BorderLayout.CENTER);
        //底部其他信息显示
        downField.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightField.add(downField,BorderLayout.SOUTH);
        downField.setLayout(new FlowLayout(FlowLayout.LEFT));
        downField.add(timeShowLabel);
        downField.add(timeLabel);
        timeLabel.setPreferredSize(new Dimension(200,15));
        downField.add(countShowLabel);
        downField.add(countLabel);

        //初始化
        //左部滚动区（将ArrayList的数据转入ListModel）
        for(Note note:userInformation.noteInformation){
            listModel.addElement(note);
        }
        //右部编辑器
        titleField.setText("");
        titleShowLabel.setText("--- 标题 -----------------------------------------------------------");
        contentShowLabel.setText("--- 正文内容 ---------------------------------------------------------");
        contentArea.setText("");
        //底部
        timeShowLabel.setText("创建/修改时间：");
        timeLabel.setText("");
        countShowLabel.setText("当前正文字数：");
        countLabel.setText("");

        //监听器
        SaveActionListener saveActionListener =new SaveActionListener();
        saveButton.addActionListener(saveActionListener);
        CreateActionListener createActionListener =new CreateActionListener();
        newNoteButton.addActionListener(createActionListener);
        DeleteActionListener deleteActionListener=new DeleteActionListener();
        deleteButton.addActionListener(deleteActionListener);
        SearchActionListener searchActionListener=new SearchActionListener();
        searchButton.addActionListener(searchActionListener);
        ScrollSelectionListener selectionListener=new ScrollSelectionListener();
        noteList.addListSelectionListener(selectionListener);
        AppWindowListener windowListener =new AppWindowListener();
        this.addWindowListener(windowListener);
        ContentDocumentListener contentDocumentListener=new ContentDocumentListener();
        contentArea.getDocument().addDocumentListener(contentDocumentListener);

        this.setSize(900,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    private class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String time=timeGetter.getTime();
            String title=titleField.getText();
            String content=contentArea.getText();
            Note newNote=new Note(time,title,content);
            int index=noteList.getSelectedIndex();
            listModel.set(index,newNote);
            userInformation.editNote(newNote,index);
            timeLabel.setText(time);
        }
    }

    private class CreateActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String time=timeGetter.getTime();
            String title="请输入标题";
            String content="请输入正文";
            Note newNote=new Note(time,title,content);
            userInformation.addNote(newNote);
            listModel.addElement(newNote);
            timeLabel.setText(time);
        }
    }

    private class DeleteActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int index=noteList.getSelectedIndex();
            if(index>0){
                userInformation.noteInformation.remove(index);
                listModel.remove(index);
                titleField.setText(" ");
                timeLabel.setText(" ");
                contentArea.setText(" ");
            }
        }
    }

    private class SearchActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String targetTitle=searchField.getText();
            int index=searchTitle(targetTitle);
            if(index!=-1){
                displaySelectedNote(index);
                noteList.setSelectedIndex(index);
                noteList.ensureIndexIsVisible(index);
            }else {
                new WrongFrame("发生错误","没有检索到对应的标题！");
            }
        }
    }

    private class ScrollSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting()){
                titleField.setEditable(true);
                contentArea.setEditable(true);
                displaySelectedNote();
            }else {
                titleField.setEditable(false);
                contentArea.setEditable(false);
            }
        }
    }

    private class ContentDocumentListener implements DocumentListener
    {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateCount();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateCount();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateCount();
        }

        private void updateCount()
        {
            Integer count=contentArea.getText().length();
            countLabel.setText(count.toString());
        }
    }

    private class AppWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            database.saveFileToDisk();
        }
    }

    //检索title,会返回第一个查找到的note，同时是大小写【不】敏感的
    private int searchTitle(String targetTitle)
    {
        int index=0;//for_each无法获取index，只能手动获取
        for(Note note:userInformation.noteInformation){
            if(note.title.toLowerCase().contains(targetTitle.toLowerCase())){
                return index;
            }
            index++;
        }
        return -1;//没有检索到，要有新窗口报错
    }

    private void displaySelectedNote()
    {
        Note selectedNote=noteList.getSelectedValue();
        if(noteList.getSelectedIndex()==0){
            this.deleteButton.setEnabled(false);
            titleField.setEditable(false);
            contentArea.setEditable(false);
        }else {
            this.deleteButton.setEnabled(true);
            titleField.setEditable(true);
            contentArea.setEditable(true);
        }
        if(selectedNote!=null){
            currentNote=selectedNote;
            titleField.setText(currentNote.title);
            timeLabel.setText(currentNote.time);
            contentArea.setText(currentNote.content);
        }
    }

    private void displaySelectedNote(int index)
    {
        Note selectedNote=userInformation.noteInformation.get(index);
        if(noteList.getSelectedIndex()==0){
            this.deleteButton.setEnabled(false);
            titleField.setEditable(false);
            contentArea.setEditable(false);
        }else {
            this.deleteButton.setEnabled(true);
            titleField.setEditable(true);
            contentArea.setEditable(true);
        }
        if(selectedNote!=null){
            currentNote=selectedNote;
            titleField.setText(currentNote.title);
            timeLabel.setText(currentNote.time);
            contentArea.setText(currentNote.content);
        }
    }
}
