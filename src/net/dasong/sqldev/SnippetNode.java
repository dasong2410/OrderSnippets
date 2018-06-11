package net.dasong.sqldev;

import org.w3c.dom.Node;

public class SnippetNode implements Comparable<SnippetNode>
{
    private String name;
    private Node node;

    public SnippetNode(String name, Node node)
    {
        this.name = name;
        this.node = node;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Node getNode()
    {
        return node;
    }

    public void setNode(Node node)
    {
        this.node = node;
    }

    @Override
    public int compareTo(SnippetNode o)
    {

        return name.compareTo(o.name);
    }
}
