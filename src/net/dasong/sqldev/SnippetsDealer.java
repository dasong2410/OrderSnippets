package net.dasong.sqldev;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.dasong.common.Constants;

public class SnippetsDealer {

	/*
	 * group节点排序
	 */
	public void sortGroups(Element root, ArrayList<SnippetNode> al) {
		NodeList snippetList = root.getChildNodes();

		for (int i = 0; i < snippetList.getLength(); i++) {
			root.removeChild(snippetList.item(i));
		}

		Object[] snippetNodes = al.toArray();
		Arrays.sort(snippetNodes);

		for (Object snippetNode : snippetNodes) {
			// System.out.println(((SnippetNode) snippetNode).getName());

			root.appendChild(((SnippetNode) snippetNode).getNode());
		}
	}

	/*
	 * snippet节点排序
	 */
	public void sortSnippets(Node groupNode) {
		NodeList snippetList = groupNode.getChildNodes();

		ArrayList<SnippetNode> al = new ArrayList<SnippetNode>();

		for (int j = 0; j < snippetList.getLength(); j++) {
			Node snippetNode = snippetList.item(j);

			if (snippetNode.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			SnippetNode snippet = new SnippetNode(snippetNode.getAttributes().getNamedItem("name").toString(),
					snippetNode);
			al.add(snippet);
		}

		for (int j = 0; j < snippetList.getLength(); j++) {
			groupNode.removeChild(snippetList.item(j));
		}

		Object[] snippetNodes = al.toArray();
		Arrays.sort(snippetNodes);

		for (Object snippet : snippetNodes) {
			groupNode.appendChild(((SnippetNode) snippet).getNode());
		}
	}

	/*
	 * 
	 */
	public void deal(String newXmlFile) {
		ArrayList<SnippetNode> al = new ArrayList<SnippetNode>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(Constants.SNIPPETS_FILE);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element root = doc.getDocumentElement();
		NodeList groupList = doc.getElementsByTagName("group");

		// 遍历所有group节点，对group子节点排序，将group节点保存到ArrayList中
		for (int i = 0; i < groupList.getLength(); i++) {
			Node groupNode = groupList.item(i);

			// snippet节点排序
			sortSnippets(groupNode);

			SnippetNode group = new SnippetNode(groupNode.getAttributes().getNamedItem("category").toString(),
					groupNode);

			al.add(group);
		}

		// group节点排序
		sortGroups(root, al);

		// 排序后的xml输出
		doc2XmlFile(doc, newXmlFile);
	}

	public void doc2XmlFile(Document document, String filename) {
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: java -jar OrderSnippets.jar newSnippetsFile");

			System.exit(1);
		}

		String newSnippetsFile = args[0];

		SnippetsDealer snippetsDealer = new SnippetsDealer();

		System.out.println("Start...");

		snippetsDealer.deal(newSnippetsFile);

		System.out.println("End...");
	}

}
