public class TreeNode {

    private TreeNode leftNode=null;
    private TreeNode rightNode=null;
    private double condition=-1;
    private String attribute="";
    private double Approved=0;
    private double Dennied=0;
    private int attributeIndex=-1;
    private TreeNode parentNode=null;
    private String classLabel="";
    private TreeNode sibling=null;

    public void setSibling(TreeNode node){
        sibling =node;
    }
    public TreeNode getSibling(){
       return this.sibling;
    }

    public void setClassLabel(){
        if(Approved>=Dennied){
            classLabel="True";
        }else{
            classLabel="False";
        }
    }

    public String getClassLabel(){
        return this.classLabel;
    }

    public void setParentNode(TreeNode node){
        this.parentNode=node;
    }


    public TreeNode getParentNode(){
        return this.parentNode;
    }

    public void setLeftNode(TreeNode node){
        leftNode=node;
    }
    public void setRightNode(TreeNode node){
        rightNode=node;
    }

    public void setCondition(double condition){
        this.condition=condition;
    }

    public void setAttribute(String attribute){
        this.attribute=attribute;
    }

    public void setApproved(double rApproved){
        this.Approved=rApproved;
    }
    public void setDennied(double rDennied){
        this.Dennied=rDennied;
    }

    public TreeNode getLeftNode(){
        return this.leftNode;
    }
    public TreeNode getRightNode(){
        return this.rightNode;
    }

    public double getApproved(){
        return this.Approved;
    }

    public double getDennied(){
        return this.Dennied;
    }

    public String getAttribute(){
        return this.attribute;
    }

    public int getAttributeIndex(){
        return this.attributeIndex;
    }

    public void setAttributeIndex(int aIndex){
        this.attributeIndex=aIndex;
    }

    public double getCondition(){
        return this.condition;
    }
    public void traverse(int index){
        System.out.println(index+" Attribute: "+attribute+" <condition : "+ condition);
        //System.out.println("Approved: "+getApproved() );
        //System.out.println("Dennied: "+getDennied());
        //System.out.println("Attrinute Index: "+ getAttributeIndex());
        if(leftNode!=null) {
            leftNode.traverse(2*index );
        }
        if(rightNode!=null) {
            rightNode.traverse(2*index + 1);
        }
    }
}
