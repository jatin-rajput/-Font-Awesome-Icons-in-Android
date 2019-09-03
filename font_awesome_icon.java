1. Font Awesome Android Library

In order to use the font awesome icon collection, you have to add the font awesome font files to your assets directly and apply the appropriate fontFace on to TextView which is simpler task. But we want the icons to be displayed in other widgets also like in Buttons, Menus, Bottom Navigation and Navigation Drawer etc,. Displaying icon in non-textual views takes some effort.
So I have written a library by incorporating all the necessary methods to render the icons in multiple widgets.

To get started, include the fontawesome in your build.gradle and sync your project.

dependencies {
    // font awesome
    implementation 'info.androidhive:fontawesome:0.0.5'
}

2. How to use it?
2.1 Displaying Icon in TextView

The easiest way to display the font icon is, use the FontTextView text by setting the appropriate icon name as text value. This widget extends from AppCompatTextView, 
so all the text view attributes applies to this widget.

<info.androidhive.fontawesome.FontTextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/fa_calendar_check_solid"
            android:textColor="@color/icon_color"
            android:textSize="@dimen/icon_size"
            app:solid_icon="true" />
            
solid_icon: To display solid icon, set this value as true.
brand_icon: To display brand icon, set this value as true.

2.2 Using FontDrawable
Using the icon in xml layout is easy, but if you want to use the icon for other widget like button or menu, you can use the FontDrawable element to apply the icon.

For an example, if you want to use the font awesome icon to Floating Action Button, you can use the FontDrawable as shown below.

FloatingActionButton fab = findViewById(R.id.fab);
 
// using paper plane icon for FAB
FontDrawable drawable = new FontDrawable(this, R.string.fa_paper_plane_solid, true, false);
 
// white color to icon
drawable.setTextColor(ContextCompat.getColor(this, android.R.color.white));
fab.setImageDrawable(drawable);
font-awesome-floating-action-button

2.3 Using in Menus (Bottom Navigation, Navigation Drawer etc.,)
You can also use the font icons in the widgets that uses menu file to render the items like Toolbar icons, Bottom Navigation, Navigation Drawer etc., To render the icon in menus, you can use the FontDrawable to set the icons, but it needs few hacks through java code.

The below example shows rending the font awesome icons in Navigation Drawer menu. Full example can be found here.

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        intDrawerLayout();
    }
 
    /**
     * Changing navigation drawer icons
     * This involves looping through menu items and applying icons
     */
    private void intDrawerLayout() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
 
        ImageView iconHeader = navigationView.getHeaderView(0).findViewById(R.id.nav_header_icon);
        FontDrawable drawable = new FontDrawable(this, R.string.fa_font_awesome, false, true);
        drawable.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        drawable.setTextSize(50);
        iconHeader.setImageDrawable(drawable);
 
        int[] icons = {
                R.string.fa_home_solid, R.string.fa_calendar_alt_solid, R.string.fa_user_solid,
                R.string.fa_heart_solid, R.string.fa_comment_solid, R.string.fa_dollar_sign_solid, R.string.fa_gift_solid
        };
        renderMenuIcons(navigationView.getMenu(), icons, true, false);
 
        int[] iconsSubmenu = {R.string.fa_cog_solid, R.string.fa_sign_out_alt_solid};
 
        renderMenuIcons(navigationView.getMenu().getItem(7).getSubMenu(), iconsSubmenu, true, false);
    }
 
    /**
     * Looping through menu icons are applying font drawable
     */
    private void renderMenuIcons(Menu menu, int[] icons, boolean isSolid, boolean isBrand) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (!menuItem.hasSubMenu()) {
                FontDrawable drawable = new FontDrawable(this, icons[i], isSolid, isBrand);
                drawable.setTextColor(ContextCompat.getColor(this, R.color.icon_nav_drawer));
                drawable.setTextSize(22);
                menu.getItem(i).setIcon(drawable);
            }
        }
    }
}

I hope you like this library. 
            
