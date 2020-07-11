# Graphics
Graphics lib of NDSLib

## Display
Display is made of JFrame(so Swing)
```Java
new Display("title",%BufferSize%,%OnDisplayRect%)
```
BufferSize is 2 is Double Buffer. 3 is Triple Buffer.
OnDisplayRect will decide Window Pos and Size
### Layer
layer allows you easy operation.

e.g. for get Layer from Display LayerManager
```Java
Display.layerManager.get("%String id%")
Diaplay.layerManager.get(%Frame Layer Num%)
```
### Drawable
```Java
new Drawable(new IDrawable(%SomeThing%))
```

and Drawable register to Display Layer.
```Java
Layer.addDrawable(Drawable)
```

### SyncedDrawable
If You Want to Changed State of Drawable,It's not hard.

```Java
new Drawable(new Synced...())
```

IDrawable which is name starts with "Synced",It Supports dynamic Value Changing.

e.g. SyncedStringDrawable

```Java
SyncedStringDrawable s_d=new SyncedStringDrawable("Before Change",new Rect(100,100,200,200),"id");
display.layerManager.get("default").add(new Drawable(s_d));
s_d.setText("After Change");
while(true){
if (display.limiter.onUpdate()) display.update();
}
```

It will draw "After Change",(Because It was Changed!)

#### CustomDrawable
if you want make CutomDrawable,
you must implement IDrawable
```Java
public class test_cutom_Drawable implments IDrawable{
    @Override
    public void onDraw(Graphics g, Rect rect) {
      //To SomeThing draw.
    }
    
    @Override
    public Rect getShowingRect(){
        //Retrun border of Showing Content
    }
    
    @Override
    public boolean isShowing(Display display){
        //returns Is this showing.
        //Usually Use Display.isShowing(Rect rect)
        return display.isShowing(getShowingRect());
    }
    
    @Override
    public String getID(){
        return "Drawable ID";
    }
} 
```

and Use this for
```Java
layer.addDrawable(new test_custom_drawable());
```

You can add own Drawable!
(DefaultCustomDrawables is LineDrawable,StringDrawable,PointDrawable)

### GUI
The diffrence between GUI and Drawable is GUI always Draw.
(EASY!)

To make;
```Java
new GUIBase("test",new Pos(),"id")
```

and register,
```Java
Display.addDrawable(new Drawable(GUIBase))
```

#### CustomGUI
if you want make CutomGUI,
you must implement IDrawable.

```Java
public class test_cutom_GUI implments IDrawable{
@Override
    public void onDraw(Graphics g, Rect rect) {
      //To SomeThing draw.
    }
    
    @Override
    public Rect getShowingRect(){
        //Retrun border of Showing Content
    }
    
    @Override
    public boolean isShowing(Display display){
        //It is GUI,so We always draw it.
        return true;
    }
    
    @Override
    public String getID(){
        return "GUI ID";
    }
} 
```
and Use this for
```Java
new Drawable(new test_custom_GUI())
```
