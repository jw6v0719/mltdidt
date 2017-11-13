public class TreeNode {

    private TreeNode leftNode=null;
    private TreeNode rightNode=null;
    private double condition=-1;
    private String attribute="";
    private double Approved=-1;
    private double Dennied=-1;


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

    public double getApproved(){
        return this.Approved;
    }

    public double getDennied(){
        return this.Dennied;
    }

    public String getAttribute(){
        return this.attribute;
    }

    public double getCondition(){
        return this.condition;
    }
    public void traverse(int index){
        System.out.println(index+" Attribute: "+attribute+" <condition : "+ condition);
        System.out.println("Approved: "+getApproved() );
        System.out.println("Dennied: "+getDennied());
        if(leftNode!=null) {
            leftNode.traverse(2*index );
        }
        if(rightNode!=null) {
            rightNode.traverse(2*index + 1);
        }
    }
}
