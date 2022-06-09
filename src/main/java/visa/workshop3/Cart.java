package visa.workshop3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<String> contents = new LinkedList<>();
    private String username;

    public Cart(String name){
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public void add(String item){
        for(String inCart: contents)
            if(inCart.equals(item))
                return;
        contents.add(item);
    }
    
    public String delete(int index){
        if( index < contents.size())
            return contents.remove(index);
        return "nothing";
    }

    public void load(InputStream is) throws IOException {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String item;
        while((item = br.readLine()) != null)
            contents.add(item);
        br.close();
        isr.close();
    }

    //load all the cotent on the file, InputStream is an IO
    //InputStreamReader cannot read line by line, hence need BufferedReader
    //if and while lines, if more than one line, need curly brackets

    public void save(OutputStream os) throws IOException{
        OutputStreamWriter ows = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(ows);
        for(String item: contents)
            bw.write(item + "\n");
            
        //for output stream, just follow first in last out to be consistent
        ows.flush();
        bw.flush();
        bw.close();
        ows.close();
        //br first. follow the first in last out rule
    }
    //saving is for output, loading is for input
    public List<String> getContents(){
        return contents;
    }
}