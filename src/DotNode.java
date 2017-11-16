public class DotNode {

    private double samples=0;
    private String attributes="";
    private double approved=0;
    private double denied=0;
    private String comparison="&lt";
    private double condition=0;
    private String classLabel="";
    private String posColorCode="#399de506";
    private String negColorCode="#e58139d0";
    private int index=0;
    public DotNode(TreeNode node,  int index){
        this.index=index;
        this.attributes=node.getAttribute();
        this.approved=node.getApproved();
        this.denied=node.getDennied();
        this.samples=approved+denied;
        this.classLabel=node.getClassLabel();
        //this.comparison=comparison;
        this.condition=node.getCondition();
    }
    public String getNodeInfo(){
        String colorCode=(approved>=denied)?posColorCode:negColorCode;
//        if(comparison.equals("<")){
//            comparison="&lt";
//        }else{
//            comparison="&ge";
//        }
        String nodeInfo="";
        if(condition==-1){
            nodeInfo=index+
                    " [label=<Leaf <br/>samples = "+samples+
                    "<br/>denied: "+denied+
                    ", approved: "+approved+"<br/> class = "+classLabel+
                    ">, fillcolor=\""+colorCode+"\"] ;\n";
        }else{
            nodeInfo=index+
                " [label=<"+attributes+
                        " "+comparison+"; "+condition+"<br/>samples = "+samples+
                        "<br/>denied: "+denied+
                        ", approved: "+approved+"<br/> class = "+classLabel+
                        ">, fillcolor=\""+colorCode+"\"] ;\n";
        }
        return nodeInfo;
    }

}
