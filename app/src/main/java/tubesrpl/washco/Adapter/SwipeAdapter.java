package tubesrpl.washco.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import tubesrpl.washco.R;

public class SwipeAdapter extends PagerAdapter {
    private int[] image_resources = {R.drawable.promo1, R.drawable.promo2, R.drawable.promo3, R.drawable.promo4};
    private Context context;
    private LayoutInflater layoutInflater;

    public SwipeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        ImageView imageView = item_view.findViewById(R.id.image_view);

        imageView.setImageResource(image_resources[position]);
        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
