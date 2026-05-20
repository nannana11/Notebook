package Notebook;

import java.io.Serializable;
import java.util.ArrayList;

public class UserInformation implements Serializable {
    public String userName;
    public String password;
    public ArrayList<Note> noteInformation=new ArrayList<>();

    public UserInformation(String userName,String password)
    {
        this.userName=userName;
        this.password=password;
        TimeGetter timeGetter=new TimeGetter();
        String time=timeGetter.getTime();
        addNote(time,"应用介绍与使用教程",tutorial);
    }

    public void addNote(String time,String title,String content)
    {
        Note newNote=new Note(time,title,content);
        noteInformation.add(newNote);
    }

    public void addNote(Note newNote)
    {
        noteInformation.add(newNote);
    }

    public void editNote(String time,String title,String content,int index)
    {
        Note newNote=new Note(time,title,content);
        noteInformation.set(index,newNote);
    }

    public void editNote(Note newNote,int index)
    {
        noteInformation.set(index,newNote);
    }

    private String tutorial="""
        💻 【笔记本应用】
        一个非常方便的电脑桌面随手记应用 💡
        
        📢 [应用介绍]
        -----------------------------------------------------
        ✨ 适用场景
          - 💻 课堂记录笔记
          - 📝 每日记账
          - 📅 日程安排
          - ⛺ 好词好句摘抄
        
        🛠️ 功能特点
          - 🚀 【随时随地记笔记】没有花里胡哨的复杂功能，界面简单方便，使用起来极为【便捷】！
          - 🔐 【账号登陆】密码登陆，隐私性极强！不同账号记录不同内容，笔记方便好管理！
          - 🔍 【标题检索】搜索框可以随时根据笔记标题进行检索！立即找到想要找的笔记！
          - ⏰ 【时间记录】精准到每条笔记创建或最新修改的时间！
          - 🧮 【实时字数显示】最直观记录当前有多少字，告别糊涂数渣渣！
          - 💾 【本地保存】离线模式笔记全部保存在本地，完全不用担心隐私泄露！每次登陆，一键加载以前的笔记！
        
        📄 [使用教程]
        -----------------------------------------------------
        📌 登陆与注册
          - 请在第一个第二个文本框内，依次输入已经创建过的用户名和密码，勾选同意用户协定后，方可登陆。
          - 若没有账号，可以点勾选同意用户协定后，点击注册按钮，注册新账号。
          
          ⚠️ 特别提醒：
            1. 登陆以及注册时请勾选【同意用户协定】，方可点击“登陆”和“注册”按钮。
            2. 用户名和密码区分大小写。
            3. 注册账号时，请不要使用和已注册过的账号相同的用户名。
        
        📌 笔记本使用
          - 每个账号创建时会自动生成“应用介绍和教程”，此笔记无法编辑与删除。
          - 新建、保存与删除：界面最上方为功能区。每次需要新建一条笔记，请点击“新建”按钮。每次笔记编辑完成后请点击“保存”按钮。若要删除选中笔记，请在选中笔记后点击“删除”。
          - 检索笔记：界面最上方有搜索功能。可在文本框内输入想要检索的内容，点击“检索”按钮，即可自动选中检索到的笔记。同时支持大小写不敏感笔记，检索会检索【标题】，同时仅返回检索到的第一条笔记。如果有多条笔记含有目标内容，仅会返回第一个。
          - 选择笔记：界面左侧有全部笔记的展示区。展示有每则笔记的“标题”与“创建或最新修改时间”。【单击】即可选中该笔记，同时右侧会显示选中笔记的笔记内容。
          - 编辑笔记内容：界面右侧为笔记内容展示与编辑区，可以随意修改笔记内容。
          - 底部功能区：界面最下方会显示选中的笔记最新的修改时间以及当前段落的实时文本字数。
          
          ⚠️ 特别提醒：
            - 目前笔记不具备【自动保存】功能，请在每次修改后手动保存笔记！若在切换选中笔记之前没有保存当前笔记，则修改内容会丢失。
        
        🤝 [获取应用]
        -----------------------------------------------------
        - 本项目全部开源到 Github，仓库地址为：https://github.com/nannana11/Notebook
        - 源代码：本项目源代码为多个 Java 文件，可以直接从仓库内获取。
        - Release：目前已经上传 .jar 文件，后续会更新上传 .exe 等文件。
        
        ⚙️ [开发说明与版本情况]
        -----------------------------------------------------
        开发说明：
          - ☕ 本项目使用 Java 语言进行开发，GUI 选择了使用 Java 自带的 Swing 库。
          - 💻 项目目前仍在开发中，各个功能正在逐步完善。敬请期待。
          - 🌐 应用目前仅支持离线版本，后续会开发联网版本。
        
        版本更新情况：
          - 26年5月20日更新，目前版本为 1.0 版本。
        
        📫 [反馈与联系作者]
        -----------------------------------------------------
        📧 一号作者邮箱: 2025141460134@stu.scu.edu.cn
        📧 二号作者邮箱: 3381194371@qq.com
        """;
}
