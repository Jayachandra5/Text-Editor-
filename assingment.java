/**
 * 
 * @autour K Jayachandra
 * 
 */

package snu_work;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.*;
import java.util.logging.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class assingment extends JFrame {
    
    
    public void save(ActionEvent evt) {

        int flag = fileChooser.showSaveDialog(null);
        if (flag == JFileChooser.APPROVE_OPTION) {

            File saveFiles = fileChooser.getSelectedFile();
            StyledDocument documents = textArea.getStyledDocument();
            RTFEditorKit editorKits = new RTFEditorKit();
            BufferedOutputStream outputStream;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(saveFiles));
                editorKits.write(outputStream, documents, documents.getStartPosition().getOffset(), documents.getLength());
                outputStream.flush();
                outputStream.close();
            } catch (BadLocationException e1) {
                e1.getCause();
            } catch (IOException e1) {
                e1.getSuppressed();
            }

            fileName = fileChooser.getName(saveFiles);
            this.setTitle(fileName + ".rtf");
            currentFileSaved=1;
        }

    }

    public void open(ActionEvent evt) {
       
        JFileChooser fileChooser = new JFileChooser();
        textArea.setText(" ");
        fileChooser.showOpenDialog(null);
        openedFile = fileChooser.getSelectedFile();
        RTFEditorKit editorKit = new RTFEditorKit();
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(openedFile);
            editorKit.read(inputStream,textArea.getStyledDocument(), 0);
            inputStream.close();
        } catch (BadLocationException e1) {
            e1.getCause();
        } catch (IOException e2) {
            e2.printStackTrace();
        }

    }

    public void newfile(ActionEvent evt) {
        
        if(currentFileSaved==0){
            this.save(evt);

        }else{
            this.setTitle("New Text Document");
            textArea.setText(" ");
            textArea.setFont(newFont);
            currentFileSaved=0;
        }

    }

    public void action(ActionEvent evt) {

        int flag = fileChooser.showSaveDialog(null);
        if (flag == JFileChooser.APPROVE_OPTION) {

            File saveFiles = fileChooser.getSelectedFile();
            StyledDocument documents = textArea.getStyledDocument();
            RTFEditorKit editorKits = new RTFEditorKit();
            BufferedOutputStream outputStream;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(saveFiles));
                editorKits.write(outputStream, documents, documents.getStartPosition().getOffset(), documents.getLength());
                outputStream.flush();
                outputStream.close();
            } catch (BadLocationException e1) {
                e1.getCause();
            } catch (IOException e1) {
                e1.getSuppressed();
            }

            fileName = fileChooser.getName(saveFiles);
            this.setTitle(fileName + ".rtf");
            currentFileSaved=1;

        }

    }

    public void cut(ActionEvent evt) {
       
        document = (StyledDocument) textArea.getDocument();
        int sel = textArea.getSelectionStart();

        element = document.getCharacterElement(sel);
        attribute = element.getAttributes();

        copiedText = textArea.getSelectedText();

        try {
            System.out.println("CUT");
            document.remove(sel, textArea.getSelectedText().length());
        } catch (BadLocationException ex) {
            Logger.getLogger(assingment.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void copy(ActionEvent evt) {
        
        document = (StyledDocument) textArea.getDocument();
        
        int selection = textArea.getSelectionStart();

        element = document.getCharacterElement(selection);
        attribute = element.getAttributes();

        copiedText = textArea.getSelectedText();

    }

    public void action1(ActionEvent evt) {

        try {
            
            document.insertString(textArea.getCaretPosition(), " " + copiedText + " ", attribute);
        } catch (BadLocationException ex) {}    }

    public void bold(java.awt.event.ActionEvent evt) {
       
        StyledDocument docment = (StyledDocument) textArea.getDocument();
        int stop = textArea.getSelectionEnd();
        int start = textArea.getSelectionStart();

        Element elemenmt = docment.getCharacterElement(start);
        AttributeSet attriubet =  elemenmt.getAttributes();

        if(stop == start){

            return;
        }

        MutableAttributeSet asNew = new SimpleAttributeSet(attriubet.copyAttributes());
        StyleConstants.setBold(asNew, !StyleConstants.isBold(attriubet));
        docment.setCharacterAttributes(start, textArea.getSelectedText().length(), asNew, true);

    }
    public void italic(ActionEvent evt) {

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as =  element.getAttributes();

        if(selectionEnd == selectionStart){

            return;
        }

        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setItalic(asNew, !StyleConstants.isItalic(as));
        doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                .length(), asNew, true);


    }

    public void underline(ActionEvent evt) {
       
        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as =  element.getAttributes();

        if(selectionEnd == selectionStart){

            return;
        }

        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setUnderline(asNew, !StyleConstants.isUnderline(as));
        doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                .length(), asNew, true);

    }

    public void action2(ActionEvent evt) {
      
        StyledDocument ff = (StyledDocument) textArea.getDocument();
        int ssq = textArea.getSelectionEnd();
        int ss = textArea.getSelectionStart();

        Element element = ff.getCharacterElement(ss);
        AttributeSet as =  element.getAttributes();

        if(ssq == ss){

            return;
        }

        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setStrikeThrough(asNew, !StyleConstants.isStrikeThrough(as));
        ff.setCharacterAttributes(ss, textArea.getSelectedText()
                .length(), asNew, true);

    }

    
    public void Lable2(){

        String s;
        int counter=0;

        if(textArea.getSelectedText()==null){
            s = textArea.getText();
        }else{
            s = textArea.getSelectedText();
            counter=1;
        }

        int words = 0;
        int characters = 0;

        int flag=0;
        if(s.isEmpty()){
            words=0;
            characters=0;
        }else{

            String[] array = s.split(" ");

            for(String d :array){

                String[] newLine = d.split("\n");
                if(newLine.length != 1){
                    flag=1;
                    for(String line:newLine){
                        if(!line.equals("")){
                            words++;
                        }
                        characters = characters + line.length();
                    }
                }

                if(flag==0){
                    words++;
                    characters = characters + d.length();
                }
                flag=0;
            }


        }

        Lable4.setText(String.valueOf(characters));
        Lable2.setText(String.valueOf(words));
        if(counter==1)
            Lable5.setText("WORD COUNT OF SELECTED TEXT ONLY");
        else
            Lable5.setText("WORD COUNT OF ENTIRE DOCUMENT");

    }


    public void mouse(MouseEvent evt) {Lable2(); }

    public void relase(KeyEvent evt) { Lable2();  }

    public void cBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedFont = (String)cBox1.getSelectedItem();
        Font f = new Font(selectedFont,Font.PLAIN,previousSize);

        if(textArea.getSelectedText()==null){
            textArea.setFont(f);
        }else{

            StyledDocument doc = (StyledDocument) textArea.getDocument();
            int selectionStart = textArea.getSelectionStart();

            Element elements = doc.getCharacterElement(selectionStart);
            AttributeSet atrridut =  elements.getAttributes();

            MutableAttributeSet asNew = new SimpleAttributeSet(atrridut.copyAttributes());
            StyleConstants.setFontFamily(asNew, selectedFont);
            doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                    .length(), asNew, true);
        }

        previousFont = selectedFont;
    }

    public void action5(ActionEvent evt) {

        String s = cBox2.getSelectedItem().toString();
        int selectedSize = Integer.valueOf(s);

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as =  element.getAttributes();

        if(selectionEnd == selectionStart){
            Font newFonts = new Font(previousFont,Font.PLAIN,selectedSize);
            textArea.setFont(newFonts);
            return;
        }
        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setFontSize(asNew, selectedSize);
        doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                .length(), asNew, true);

        previousSize = selectedSize;

    }
    
    public void highlight(JTextComponent textComponent, String s, Highlighter.HighlightPainter colourChoice){

        try{
            Highlighter h = textComponent.getHighlighter();
            Document doc = textComponent.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            while((pos = text.toUpperCase().indexOf(s.toUpperCase(),pos))>=0){

                h.addHighlight(pos, pos+s.length(), colourChoice);
                pos += s.length();
            }
        }catch(BadLocationException e){
            System.out.println(e.getMessage());
        }

    }

    public static void findNextHighlight(JTextComponent textComponent, String s, Highlighter.HighlightPainter colourChoice){

        try{
            Highlighter h = textComponent.getHighlighter();
            Document doc = textComponent.getDocument();
            String text = doc.getText(0, doc.getLength());

            if((findNextPos = text.toUpperCase().indexOf(s.toUpperCase(),findNextPos))>=0){
              
                h.addHighlight(findNextPos, findNextPos+s.length(), colourChoice);
                findNextPos += s.length();
            }
        }catch(BadLocationException e){

            System.out.println(e.getMessage());

        }
    }

    public void removeHighlighter(JTextComponent textField, int remove){

        Highlighter highlite = textArea.getHighlighter();
        Highlighter.Highlight[] highlites = highlite.getHighlights();

        if(remove==1){
            for (int i = 0; i < highlites.length; i++) {
                if (highlites[i].getPainter() instanceof MyHighlighter_Painter) {
                    highlite.removeHighlight(highlites[i]);
                }
            }
        }
    }

        public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            
            String s = textArea.getText();
            if(count>0){
                count=0;
                removeHighlighter(textArea,1);
            }
            if(s.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter the word to be searched");
            }else{
                String[] array = s.split(" ");
                for(String arrayOne:array){
                    if(arrayOne.contains("\n")){
                        String[] newLineArray = arrayOne.split("\n");
                        for(String newLineSearch: newLineArray){
                            if(newLineSearch.contains(Textfiled1.getText())){
                                count++;
                            }
                        }
                    }else{
                        if(arrayOne.contains(Textfiled1.getText())){
                            count++;
                        }
                    }
                }

            }

            foundInPlaces.setText("Found in " + count + " places");

            highlight(textArea,Textfiled1.getText(),myHighlightPainter);

        }


        public void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
            
            StyledDocument doc = (StyledDocument) textArea.getDocument();

            int pos = findNextPos - Textfiled1.getText().length();

            try {

                Element element = doc.getCharacterElement(pos);
                AttributeSet as =  element.getAttributes();
                doc.remove(pos, Textfiled1.getText().length());
                doc.insertString(pos, TextField2.getText(), as);

            } catch (BadLocationException ex) {
                Logger.getLogger(assingment.class.getName()).log(Level.SEVERE, null, ex);
            }


        }

        public void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
            
            if (Textfiled1.getText().equals(previousSearchedText)) {

                previousSearchedText = Textfiled1.getText();
                findNextPos = 0;
            }

            if (findNextPos == 0) {
                findNextHighlight(textArea, Textfiled1.getText(), myHighlightPainter);
            } else {

                try {
                    Document doc = textArea.getDocument();
                   doc.getText(0, doc.getLength());

                    removeHighlighter(textArea, 1);
                    findNextHighlight(textArea, Textfiled1.getText(), myHighlightPainter);

                    oldFindNextPos = findNextPos;

                } catch (BadLocationException e) {

                    System.out.println(e.getMessage());
                }

            }
        }

        public void jButton2ActionPerformed(java.awt.event.ActionEvent evt){

            removeHighlighter(textArea,1);

          
            StyledDocument doc = (StyledDocument) textArea.getDocument();


            int pos=0;
            try {

                while((pos = textArea.getText().toUpperCase().indexOf(Textfiled1.getText().toUpperCase(), pos))>=0){

                    System.out.println(pos);

                    Element element = doc.getCharacterElement(pos);
                    AttributeSet as =  element.getAttributes();
                    doc.remove(pos, Textfiled1.getText().length());
                    doc.insertString(pos, TextField2.getText(), as);

                    pos+= TextField2.getText().length();

                }
            } catch (BadLocationException ex) {
                Logger.getLogger(assingment.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    public void toLowerActionPerformed(java.awt.event.ActionEvent evt) {
       
        String s = textArea.getSelectedText().toUpperCase();

        StyledDocument doc = (StyledDocument) textArea.getStyledDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element elements = doc.getCharacterElement(selectionStart);
        AttributeSet as =  elements.getAttributes();

        if(selectionEnd == selectionStart){
            return;
        }

        try {
            doc.remove(selectionStart, s.length());
            doc.insertString(selectionStart, s.toLowerCase(), as);
        } catch (BadLocationException ex) {
            Logger.getLogger(assingment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
class MyHighlighter_Painter extends DefaultHighlighter.DefaultHighlightPainter {

    public MyHighlighter_Painter(Color c) {
        super(c);
    }
}

    public void toUpperActionPerformed(java.awt.event.ActionEvent evt) {

        String s = textArea.getSelectedText().toUpperCase();

        StyledDocument doc = (StyledDocument) textArea.getStyledDocument();
       
        int selectionStart = textArea.getSelectionStart();

        Element elements = doc.getCharacterElement(selectionStart);
        AttributeSet as =  elements.getAttributes();

        try {
            doc.remove(selectionStart, s.length());
            doc.insertString(selectionStart, s.toUpperCase(), as);
        } catch (BadLocationException ex) {
            Logger.getLogger(assingment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    //FONTS
    String[] fonts = {"Arial", "Times New Roman", "Calibri", "Georgia", "Helevetic", "Monospaced", };
    String[] fontSizes = {"5","10","15","20","25","30","35","40","45","50"};
    
    //PANELS
    public JPanel first = new JPanel();
    public JPanel second = new JPanel();
    public JPanel third = new JPanel();
    public JPanel fourt = new JPanel();
    public JPanel five = new JPanel();
    public JPanel six = new JPanel();
    public JPanel seven = new JPanel();
    public JPanel eight = new JPanel();
    public JPanel nine = new JPanel();
    public JPanel ten = new JPanel();
    
    //TEXT AREA
    JTextPane textArea = new JTextPane();

    //COMBO BOX
    JComboBox cBox1 = new JComboBox();
    JComboBox cBox2 = new JComboBox();
    
    //LABELS
    JLabel Lable1 = new JLabel("Word Count: ");
    JLabel Lable2 = new JLabel("");
    JLabel Lable3 = new JLabel("Character Count: ");
    JLabel Lable4 = new JLabel("");
    JLabel Lable5 = new JLabel("");
    
    //MENUS
    public JMenuBar menuBar = new JMenuBar();
    public JMenu file = new JMenu("File");
    public JMenu edit = new JMenu("Edit");
    public JMenu review = new JMenu("Review");
    public JMenu help = new JMenu("Help");
    
    //MENU ITEMS 
    public JMenuItem New = new JMenuItem("New");
    public JMenuItem Open = new JMenuItem("Open");
    public JMenuItem Save = new JMenuItem("Save");
    public JMenuItem SaveAS = new JMenuItem("Save as...");
    public JMenuItem Exit = new JMenuItem("Exit");
    
    public JMenuItem cut = new JMenuItem("Cut");
    public JMenuItem Copy = new JMenuItem("Copy");
    public JMenuItem Paste = new JMenuItem("Paste");
    public JMenuItem toLower = new JMenuItem("ToLower");
    public JMenuItem toUpper = new JMenuItem("ToUpper");

    // TEXT FIELDS
    JTextField Textfiled1 = new JTextField();
    JTextField TextField2 = new JTextField();
    
    //LABELS
    JLabel lablefind = new JLabel("Find");
    JLabel lablereplace = new JLabel("Replace");
    JLabel foundInPlaces = new JLabel("");
    
    //BUTTONS 
    JButton Button1 = new JButton("Replace");
    JButton Button2 = new JButton("Replace All");
    JButton Button3 = new JButton("Find Next");
    JButton Button4 = new JButton("Find All");

   
    //SHAPE BUTTONS
    public JButton Rectangle = new JButton("Rectangle");
    public JButton Oval = new JButton("Oval");
    public JButton line = new JButton("Line");
    public JButton Triangle = new JButton("Triangle");
    public JButton pentagon = new JButton("Pentagon");
    public JButton clear = new JButton("CLEAR");
    public JButton search = new JButton("SEARCH");

    //SEARCH FIELD
    public JTextField Searchfield = new JTextField("Type to search");

    String copiedText = " ";
    StyledDocument document;
    Element element;
    AttributeSet attribute;
    File openedFile;
    RTFEditorKit editorKit;
    JFileChooser fileChooser = new JFileChooser();
    
    Highlighter.HighlightPainter myHighlightPainter = new MyHighlighter_Painter(Color.yellow);
    Highlighter.HighlightPainter removeTheHighlight = new MyHighlighter_Painter(Color.red);
    int removeHighlights = 0;
    
    Font newFont = new Font("Calibri",Font.PLAIN,20);
    int previousSize = 20;
    String previousFont = "Arial";
    
    String fn="",dir="",fileName="New Text Document";

    Font startingFont = new Font(previousFont,Font.PLAIN,previousSize);

    int currentFileSaved = 0;
    static int findNextPos = 0;
    static int oldFindNextPos = 0;
    static int numberOfWordsFound = 0;
    int count=0;

    String previousSearchedText = "";

    assingment() {
        textArea.setBackground(new Color(240,230,140));
        eight.setBackground(new Color(240,230,140));
        six.setBackground(new Color(0,0,0));
        five.setBackground(new Color(240,230,140));
        fourt.setBackground(new Color(240,230,140));
        
        textArea.setFont(newFont);

        for(String s :fonts){
            cBox1.addItem(s);
        }

        for(String s:fontSizes){
            cBox2.addItem(s);
        }

        this.setTitle(fileName + ".rtf");

        //Sizes of main menu titles
        file.setPreferredSize(new Dimension(50,25));
        edit.setPreferredSize(new Dimension(50,25));
        review.setPreferredSize(new Dimension(70,25));
        help.setPreferredSize(new Dimension(50,25));

        //Borders on main menu titles
        file.setBorder(BorderFactory.createLineBorder(Color.white));
        edit.setBorder(BorderFactory.createLineBorder(Color.white));
        review.setBorder(BorderFactory.createLineBorder(Color.white));
        help.setBorder(BorderFactory.createLineBorder(Color.white));

        //Adding main menu titles
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(review);
        menuBar.add(help);
    

        //Adding action listeners
        New.addActionListener(this::newfile);

        Open.addActionListener(this::open);

        Save.addActionListener(this::save);

        SaveAS.addActionListener(new ActionListenerImpl());

        cut.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cut(e);
            }
        }));

        Copy.addActionListener(this::copy);

        Paste.addActionListener(this::action1);

        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Lable2();
            }
        });
        cBox1.addActionListener(this::cBox1ActionPerformed);

        cBox2.addActionListener(this::action5);

        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Lable2();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        });
        
        textArea.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Lable2();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
        });

        toUpper.addActionListener(this::toUpperActionPerformed);

        toLower.addActionListener(this::toLowerActionPerformed);
        
        Button4.addActionListener(this::jButton1ActionPerformed);

        Button2.addActionListener(this::jButton2ActionPerformed);

        Button3.addActionListener(this::jButton3ActionPerformed);

        Button1.addActionListener(this::jButton4ActionPerformed);

        //Adding menu items under 'File'
        file.add(New);
        file.add(Open);
        file.addSeparator();
        file.add(Save);
        file.add(SaveAS);
        file.addSeparator();
        file.add(Exit);

        //Adding menu items under 'Edit'

        edit.add(cut);
        edit.add(Copy);
        edit.add(Paste);
        edit.add(toLower);
        edit.add(toUpper);

        //Adding Icons for formatting
        
        JButton bold = new JButton("   Bold   ");
       
        JButton Italics = new JButton("   Italic   ");
        
        JButton Underline = new JButton("   UnderLine   ");
        
        JButton Strikethrough = new JButton("   Strick   ");
        
        five.add(bold);
        five.add(Italics);
        five.add(Underline);
        five.add(Strikethrough);
        five.setLayout(new BoxLayout(five,BoxLayout.LINE_AXIS));
       
        five.add(Box.createRigidArea(new Dimension(20,20)));
        
        five.add(Box.createRigidArea(new Dimension(20,0)));
        five.add(cBox1);
        five.add(cBox2);

        //Action Listeners
        bold.addActionListener(this::bold);

        Italics.addActionListener(this::italic);

        Underline.addActionListener(this::underline);

        Strikethrough.addActionListener(this::action2);

        //Set layouts
        second.setLayout(new BoxLayout(second,BoxLayout.LINE_AXIS));
        fourt.setLayout(new BoxLayout(fourt,BoxLayout.PAGE_AXIS));
        eight .setLayout(new BoxLayout(eight,BoxLayout.PAGE_AXIS));
        first.setLayout(new BoxLayout(first,BoxLayout.PAGE_AXIS));

        //Elements of Panel6

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        Textfiled1.setPreferredSize(new Dimension(300,15));
        TextField2.setPreferredSize(new Dimension(300,15));
        six.setLayout(new BoxLayout(six,BoxLayout.PAGE_AXIS));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        panel1.add(lablefind);

        panel1.add(Textfiled1);

        panel1.add(lablereplace);

        panel1.add(TextField2);

        six.add(panel1);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS));
        panel2.add(Button4);
        panel2.add(Box.createRigidArea(new Dimension(10,0)));
        panel2.add(Button3);
        panel2.add(Box.createRigidArea(new Dimension(10,0)));
        panel2.add(Button1);
        panel2.add(Box.createRigidArea(new Dimension(10,0)));
        panel2.add(Button2);
        six.add(panel2);
        six.add(foundInPlaces);

        //Elements of Panel7

        textArea.setPreferredSize(new Dimension(500,300));
        seven.setLayout(new BoxLayout(seven,BoxLayout.LINE_AXIS));
        seven.add(textArea);

        //Elements of Panel3

        third.setLayout(new FlowLayout(FlowLayout.LEFT));
        third.add(Lable1);
        third.add(Lable2);
        third.add(Lable3);
        third.add(Lable4);
        third.add(Lable5);
        third.setBackground(Color.LIGHT_GRAY);

        //Elements of Panel4
        fourt.add(Box.createRigidArea(new Dimension(0,10)));
        fourt.add(five);
        fourt.add(Box.createRigidArea(new Dimension(0,10)));
        fourt.add(six);
        fourt.add(Box.createRigidArea(new Dimension(0,10)));
        fourt.add(seven);
        fourt.setBorder(BorderFactory.createLineBorder(Color.black));

        //Elements of Panel2
        second.add(fourt);
        second.add(Box.createRigidArea(new Dimension(10,0)));
        second.add(eight);

        //Elements of Panel1
        this.add(menuBar,BorderLayout.NORTH);
        first.add(second);
        first.add(Box.createVerticalGlue());
        first.add(third);

        //Add Panel1 to JFrame
        this.add(first);
    }

    private class ActionListenerImpl implements ActionListener {

        public ActionListenerImpl() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            action(e);
        }
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new assingment();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(75,75);
        frame.setSize(1250,600);
        frame.setTitle("Text Editor");
    }

}