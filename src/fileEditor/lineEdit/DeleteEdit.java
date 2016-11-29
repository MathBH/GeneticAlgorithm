package fileEditor.lineEdit;

public class DeleteEdit implements LineEdit{
	private String regex;

	public DeleteEdit(String regex){
		this.regex = regex;
	}
	
	public String execute(String line) {
		return line.replaceAll(regex, "");
	}

}
