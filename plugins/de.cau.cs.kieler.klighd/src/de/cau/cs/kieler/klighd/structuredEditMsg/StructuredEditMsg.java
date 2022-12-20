package de.cau.cs.kieler.klighd.structuredEditMsg;

public class StructuredEditMsg{
    
    String label;
    String kind;
    Boolean mergable;
    String[] inputs;
    
    public StructuredEditMsg(String label, String kind, Boolean mergable,String[] inputs) {
        this.label = label;
        this.kind = kind;
        this.mergable = mergable;
        this.inputs = inputs;
    }
    
}
