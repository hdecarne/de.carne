/*
 * Copyright (c) 2007-2016 Holger de Carne and contributors, All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.carne.jfx.messagebox;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import de.carne.jfx.FXPlatform;
import de.carne.jfx.StageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Dialog controller for message box display.
 */
public class MessageBoxController extends StageController {

	private MessageBoxResult resultCmd1 = MessageBoxResult.NONE;
	private MessageBoxResult resultCmd2 = MessageBoxResult.NONE;
	private MessageBoxResult resultCmd3 = MessageBoxResult.NONE;
	private MessageBoxResult result = MessageBoxResult.NONE;

	@FXML
	ImageView ctlIcon;

	@FXML
	Label ctlMessage;

	@FXML
	TextArea ctlDetailsText;

	@FXML
	ToggleButton ctlDetailsButton;

	@FXML
	Label ctlDetailsLabel;

	@FXML
	Button ctlCmd1Button;

	@FXML
	Button ctlCmd2Button;

	@FXML
	Button ctlCmd3Button;

	@FXML
	void onToggleDetails(ActionEvent evt) {
		setDetailsExpanded(this.ctlDetailsButton.isSelected());
	}

	@FXML
	void onButton1(ActionEvent evt) {
		this.result = this.resultCmd1;
		getStage().close();
	}

	@FXML
	void onButton2(ActionEvent evt) {
		this.result = this.resultCmd2;
		getStage().close();
	}

	@FXML
	void onButton3(ActionEvent evt) {
		this.result = this.resultCmd3;
		getStage().close();
	}

	@Override
	protected void setupStage(Stage controllerStage) throws IOException {
		super.setupStage(controllerStage);
		controllerStage.setTitle(I18N.formatSTR_MESSAGEBOX_TITLE());
		this.ctlDetailsButton.managedProperty().bind(this.ctlDetailsButton.visibleProperty());
		this.ctlDetailsLabel.managedProperty().bind(this.ctlDetailsLabel.visibleProperty());
		this.ctlDetailsText.managedProperty().bind(this.ctlDetailsText.visibleProperty());
		this.ctlCmd1Button.managedProperty().bind(this.ctlCmd1Button.visibleProperty());
		this.ctlCmd2Button.managedProperty().bind(this.ctlCmd2Button.visibleProperty());
		this.ctlCmd3Button.managedProperty().bind(this.ctlCmd3Button.visibleProperty());
	}

	private void setDetailsExpanded(boolean expanded) {
		this.ctlDetailsButton.setSelected(expanded);
		this.ctlDetailsButton.setText(
				expanded ? I18N.formatSTR_DETAILS_EXPANDED_BUTTON() : I18N.formatSTR_DETAILS_COLLAPSED_BUTTON());
		this.ctlDetailsText.setVisible(expanded);
		getStage().sizeToScene();
	}

	/**
	 * Begin message display.
	 *
	 * @param message The message to display.
	 * @param details The (optional) exception causing the message.
	 * @param styles The message box style to use.
	 */
	public void beginMessageBox(String message, Throwable details, MessageBoxStyle... styles) {
		this.ctlMessage.setText(message);

		String detailsString = formatDetails(details);

		if (detailsString != null) {
			this.ctlDetailsText.setText(detailsString);
		} else {
			this.ctlDetailsButton.setVisible(false);
			this.ctlDetailsLabel.setVisible(false);
		}
		setDetailsExpanded(false);
		for (MessageBoxStyle style : styles) {
			switch (style) {
			case ICON_INFO:
				getStage().getIcons()
						.addAll(FXPlatform.stageIcons(MessageBoxImages.getImages(MessageBoxStyle.ICON_INFO)));
				this.ctlIcon.setImage(MessageBoxImages.getImage(MessageBoxStyle.ICON_INFO));
				break;
			case ICON_WARNING:
				getStage().getIcons()
						.addAll(FXPlatform.stageIcons(MessageBoxImages.getImages(MessageBoxStyle.ICON_WARNING)));
				this.ctlIcon.setImage(MessageBoxImages.getImage(MessageBoxStyle.ICON_WARNING));
				break;
			case ICON_ERROR:
				getStage().getIcons()
						.addAll(FXPlatform.stageIcons(MessageBoxImages.getImages(MessageBoxStyle.ICON_ERROR)));
				this.ctlIcon.setImage(MessageBoxImages.getImage(MessageBoxStyle.ICON_ERROR));
				break;
			case ICON_QUESTION:
				getStage().getIcons()
						.addAll(FXPlatform.stageIcons(MessageBoxImages.getImages(MessageBoxStyle.ICON_QUESTION)));
				this.ctlIcon.setImage(MessageBoxImages.getImage(MessageBoxStyle.ICON_QUESTION));
				break;
			case BUTTON_OK:
				this.ctlCmd1Button.setText(I18N.formatSTR_OK_BUTTON());
				this.ctlCmd1Button.setDefaultButton(true);
				this.resultCmd1 = MessageBoxResult.OK;
				this.ctlCmd2Button.setVisible(false);
				this.ctlCmd3Button.setVisible(false);
				break;
			case BUTTON_OK_CANCEL:
				this.ctlCmd1Button.setText(I18N.formatSTR_CANCEL_BUTTON());
				this.ctlCmd1Button.setCancelButton(true);
				this.resultCmd1 = MessageBoxResult.CANCEL;
				this.ctlCmd2Button.setText(I18N.formatSTR_OK_BUTTON());
				this.ctlCmd2Button.setDefaultButton(true);
				this.resultCmd2 = MessageBoxResult.OK;
				this.ctlCmd3Button.setVisible(false);
				break;
			case BUTTON_YES_NO:
				this.ctlCmd1Button.setText(I18N.formatSTR_NO_BUTTON());
				this.ctlCmd1Button.setCancelButton(true);
				this.resultCmd1 = MessageBoxResult.NO;
				this.ctlCmd2Button.setText(I18N.formatSTR_YES_BUTTON());
				this.ctlCmd2Button.setDefaultButton(true);
				this.resultCmd2 = MessageBoxResult.YES;
				this.ctlCmd3Button.setVisible(false);
				break;
			case BUTTON_YES_NO_CANCEL:
				this.ctlCmd1Button.setText(I18N.formatSTR_CANCEL_BUTTON());
				this.ctlCmd1Button.setCancelButton(true);
				this.resultCmd1 = MessageBoxResult.CANCEL;
				this.ctlCmd2Button.setText(I18N.formatSTR_NO_BUTTON());
				this.resultCmd2 = MessageBoxResult.NO;
				this.ctlCmd3Button.setText(I18N.formatSTR_YES_BUTTON());
				this.resultCmd3 = MessageBoxResult.YES;
				break;
			default:
				throw new RuntimeException("Unexpected style: " + style);
			}
		}
		getStage().sizeToScene();
	}

	/**
	 * Get the message box result.
	 *
	 * @return The message box result.
	 */
	public MessageBoxResult getResult() {
		return this.result;
	}

	private String formatDetails(Throwable details) {
		String detailsString = null;

		if (details != null) {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);

			details.printStackTrace(printWriter);
			detailsString = stringWriter.toString();
		}
		return detailsString;
	}

}
