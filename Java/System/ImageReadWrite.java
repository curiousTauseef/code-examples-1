// Copyright 2013 Sean McKenna
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//

// loads an image from the file system, could be used for image processing
// requires ImageReadWritePanel.java

// import image setup
import java.awt.Color;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

// image processing input
public class ImageReadWrite extends JFrame{
  
  // window attributes
  private final static int FRAME_WIDTH = 512;
  private final static int FRAME_HEIGHT = 512;
  private final static String TITLE = "Image Read and Write";
  
  public ImageReadWrite(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    this.setTitle(TITLE);
    
    Container pane = this.getContentPane();
    ImageReadWritePanel panel = new ImageReadWritePanel();
    panel.setBackground(Color.GRAY);
    pane.add(panel);
    
    this.setVisible(true);
    
    // create a file chooser
    JFileChooser chooser = new JFileChooser();
    
    // onyl allow images, extensions ",jpg", ".jpeg", and ".png"
    FileNameExtensionFilter filter =
        new FileNameExtensionFilter("JPG and PNG files",
        "jpg", "jpeg", "png");
    chooser.setFileFilter(filter);
    
    // where to start search, if possible
    File directory = new File("./test");
    chooser.setCurrentDirectory(directory);
    
    // pop up the file chooser
    int status = chooser.showOpenDialog(this);
    if(status == chooser.APPROVE_OPTION){
      File file = chooser.getSelectedFile();
      try{
        // load image into Java
        BufferedImage image = ImageIO.read(file);
        
        // add image to the panel
        panel.addImage(image);
      }catch(IOException e){}
    }
  }
  
  // create the image processor
  public static void main(String[] args){
    ImageReadWrite rw = new ImageReadWrite();
  }
}
