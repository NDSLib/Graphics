# Graphics
Graphics lib of NDSLib

## Display
Display is made of JFrame(so Swing)
```Java
new Display("title",%BufferSize%,%OnDisplayRect%)
```
BufferSize is 2 is Double Buffer. 3 is Triple Buffer.
OnDisplayRect will decide Window Pos and Size

### Drawable
```Java
new Drawable("test",new Pos(10,40),"for_id")
```
Default Drawable supported Image,String.

and Drawable register to Display,
```Java
Display.addDrawable(drawable)
```

**if You Changed state,you must register now!**


#### CustomDrawable
if you want make CutomDrawable,
you must implement ICustomDrawable
```Java
public class test_cutom_Drawable implments ICustomDrawable{
@Override
    public void onDraw(Graphics g, Rect rect) {
      //To SomeThing draw.
    }
} 
```
and Use this for
```Java
new Drawable(new test_custom_Drawable(),new Pos(),"id")
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
Display.addGUI(Gui)
```
**if You Changed state,you must register now!**

#### CustomGUI
if you want make CutomGUI,
you must implement ICustomGUI
```Java
public class test_cutom_GUI implments ICustomGUI{
@Override
    public void onDraw(Graphics g, Rect rect) {
      //To SomeThing draw.
    }
} 
```
and Use this for
```Java
new GUIBase(new test_custom_GUI(),new Pos(),"id")
```
