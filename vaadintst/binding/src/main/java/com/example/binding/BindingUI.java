package com.example.binding;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class BindingUI extends UI implements ValueChangeListener {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

    	textAreaExample();
    	//richTextAreaExample();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = BindingUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    private void richTextAreaExample() {
    	VerticalLayout layout = new VerticalLayout();
    	layout.setMargin(true);
    	layout.setSpacing(true);
    	setContent(layout);
    	
    	RichTextArea richText = new RichTextArea("Rich text area:");
    	richText.setImmediate(true);
    	richText.setSizeFull();
    	
    	Label label = new Label(richText, ContentMode.HTML);
    	
    	layout.addComponent(richText);
    	layout.addComponent(label);
    	
    }
    
    
    
    private void textAreaExample() {
    	TextField textField = new TextField("Data");
    	textField.setImmediate(true);
    	Label label = new Label();
    	
//    	Button btn = new Button("new value");
//    	btn.addClickListener( e -> {label.setValue("NEW VALUE!");});
    	
    	VerticalLayout layout = new VerticalLayout();
    	layout.addComponent(textField);
    	layout.addComponent(label);
//    	layout.addComponent(btn);
    	
    	layout.setMargin(true);
    	layout.setSpacing(true);
    	setContent(layout);
    	
    	// our TextArea component
    	TextArea textArea = new TextArea("Enter some multi-lined text and press TAB:");
    	textArea.setWidth("100px");
    	textArea.setWordwrap(false);
    	
    	textArea.addValueChangeListener(this);
    	textField.addValueChangeListener(this);
    	textArea.setImmediate(true);
    	
    	layout.addComponent(textArea);
    	
    	ObjectProperty<String> property =
    			new ObjectProperty<String>("the value");
    	textField.setPropertyDataSource(property);
    	label.setPropertyDataSource(property);
    	textArea.setPropertyDataSource(property);
    	
    }
    
    
    
	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		String userText = event.getProperty().getValue()
				.toString();
		String className = event.getProperty().getClass().getName();
		Notification.show("This is it ... " + userText + " ... " + className);
	}
}
