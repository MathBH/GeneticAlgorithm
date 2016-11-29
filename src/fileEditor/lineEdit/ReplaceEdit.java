package fileEditor.lineEdit;

public class ReplaceEdit implements LineEdit{
	private String regex;
	private String replacement;
	
	public ReplaceEdit(String regex, String replacement){
		this.regex = regex;
		this.replacement = replacement;
	}
	
	public String execute(String line){
		return line.replaceAll(regex, replacement);
	}
}
