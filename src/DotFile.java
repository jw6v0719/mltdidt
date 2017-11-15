import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DotFile {

    private String template="digraph Tree {\n" +
            "node [shape=box, style=\"filled\", color=\"black\"] ;\n";
    private String closeTemplate="}";
    private String leftLink=" [labeldistance=2.5, labelangle=45, headlabel=\"True\"] ;\n";
    private String rightLink=" [labeldistance=2.5, labelangle=45, headlabel=\"False\"] ;\n";
    private ArrayList<String> fileContent=new ArrayList<>();
    public DotFile(TreeNode root, String FileName){
        fileContent.add(template);
        write(root,1,false,true);
        fileContent.add(closeTemplate);
        writeFile(FileName);
    }

    private void writeFile(String filename){
        try {
            FileWriter fw = new FileWriter(filename);
            for(String content: fileContent) {
                fw.write(content);
            }
            fw.flush();
            fw.close();
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }

    private void write(TreeNode node,int index,boolean left,boolean root){
        if(root){
            DotNode dn=new DotNode(node,"<",1);
            fileContent.add((dn.getNodeInfo()));
        }
        else {
            if(left){
                DotNode dn=new DotNode(node,"<",index);
                fileContent.add((dn.getNodeInfo()));
                fileContent.add((index/2+" -> "+index+leftLink));
            }else{
                DotNode dn=new DotNode(node,">=",index);
                fileContent.add((dn.getNodeInfo()));
                fileContent.add(((index-1)/2+" -> "+index+rightLink));
            }
        }


        if(node.getLeftNode()!=null){
            write(node.getLeftNode(),(2*index),true,false);

        }
        if(node.getRightNode()!=null){
            write(node.getRightNode(),(2*index+1),false,false);

        }
    }

}
