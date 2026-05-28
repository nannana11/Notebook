package Notebook;

import java.io.Serializable;
import java.util.ArrayList;

public class UserInformation implements Serializable {
    public String userName;
    public String password;
    public Integer currentIndex;
    public ArrayList<Note> noteInformation=new ArrayList<>();

    public UserInformation(String userName,String password)
    {
        this.userName=userName;
        this.password=password;
        this.currentIndex=0;
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
          - 📖 课堂记录笔记
          - 📝 每日记账
          - 📅 日程安排
          - ⛺ 好词好句摘抄
        
        🛠️ 功能特点
          - 🚀 【随时随地记笔记】没有花里胡哨的复杂功能，界面简单方便，使用起来极为【便捷】！
          - 🔐 【账号登陆】密码遮罩登录，隐私性极强！不同账号记录不同内容，笔记方便好管理！
          - 🔍 【上下文高亮检索】搜索框支持“大小写不敏感”模糊匹配，一键深度检索【标题+正文】！
          - ⏰ 【时间精确记录】精准捕捉每条笔记创建或最新一次自动保存的黄金时间！
          - 🧮 【实时字数显示】正文编辑区底部配备高灵敏度字数统计，字数变动实时更新！
          - 🔄 【智能自动保存】彻底告别丢失风险！切换笔记或关闭窗口时，系统会自动安全存盘！
          - ⌨️ 【硬核快捷键支持】全面支持键盘流操作，大幅提升录入与创建效率！
          - 💾 【本地完全隔离】离线模式数据全部安全序列化保存在本地，完全不用担心隐私泄露！
        
        📄 [使用教程]
        -----------------------------------------------------
        📌 登陆与注册
          - 请在第一个、第二个输入框内，依次输入用户名和密码。自动勾选同意用户协定后会记住操作，往后直接点击登录即可。
          - 若没有账号，可直接在当前页填入新账号密码，点击注册按钮，即可完成创建。
          
          ⚠️ 特别提醒：
            1. 用户名和密码严格区分大小写。
            2. 注册账号时，请不要使用和已注册过的账号相同的用户名。
            3. 密码输入框已引入掩码防护，保护您的周边密码隐私。
        
        📌 笔记本使用
          - 每个账号创建时会自动生成本篇“应用介绍和教程”，此笔记已被系统锁死，无法编辑与删除。
          - 【新建笔记】：点击最上方的“创建”按钮（或直接按下键盘快捷键 `Alt + N`），即可在列表尾部生成一篇新笔记并自动跳转。
          - 【自动保存】：本软件现已全面支持自动保存！您在右侧编辑区修改内容后，无论是用鼠标切换到左侧其他笔记，还是直接点击右上角红叉关闭软件，系统都会自动为您存盘，无需担心文字丢失。当然，您也可以随时点击上方“保存”按钮（或按下快捷键 `Alt + S`）触发即时存盘。
          - 【删除笔记】：在左侧选中想要删除的笔记（非教程笔记），点击上方“删除”按钮即可将其彻底移除。
          - 【高级检索】：在上方文本框内输入想要检索的内容，点击“查询”按钮。系统将深度扫描所有笔记的标题和正文（大小写不敏感）。所有含有该关键字的笔记将在左侧列表被持久渲染为淡黄色，且右侧正文中的关键字也会被精准高亮染色。高亮状态将一直保持，直到您下一次点击查询新内容。
          - 【底栏面板】：界面最下方会实时更新显示选中笔记的最新修改时间，以及当前整篇笔记的精准实时字数。
        
        🤝 [获取应用与开源信息]
        -----------------------------------------------------
        - 本项目已全面开源到 GitHub，仓库地址为：https://github.com/nannana11/Notebook
        - 源代码：项目由 8 个核心 Java 源码文件协作构成，可以从仓库自由获取。
        - Release 运行版：已发布 v1.2 绿色免配置独立压缩包，内置独立 JRE，在任意 Windows 电脑上解压即可双击运行。建议右键 exe 文件将其“发送到桌面快捷方式”以获得最佳体验。
        
        ⚙️ [开发说明与版本情况]
        -----------------------------------------------------
        开发说明：
          - ☕ 本项目使用 Java 语言进行开发，GUI 选择了使用 Java 自带的 Swing 纯手工手写完成。
          - 💻 项目目前仍在持续维护与重构中，功能在逐步对齐一线文本编辑器。
        
        版本更新情况：
          - 2026年5月20日更新：发布基础功能 1.0 正式版。
          - 2026年5月25日更新：发布最新旗舰 1.2 正式版（全力攻克自动保存、多选中持久高亮检索、密码掩码与快捷键机制）。
        
        📫 [反馈与联系作者]
        -----------------------------------------------------
        📧 一号作者邮箱: 2025141460134@stu.scu.edu.cn
        📧 二号作者邮箱: 3381194371@qq.com
        -----------------------------------------------------
        感谢您的下载与使用，祝您记录愉快！
        """;

}
