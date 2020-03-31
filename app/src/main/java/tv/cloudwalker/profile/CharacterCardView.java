package tv.cloudwalker.profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.leanback.widget.BaseCardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import models.User;

public class CharacterCardView extends BaseCardView {

    public CharacterCardView(Context context) {
        super(context, null, 0);
        LayoutInflater.from(getContext()).inflate(R.layout.character_card, this);
        setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                View container = findViewById(R.id.container);
                if (hasFocus) {
                    container.setBackgroundResource(R.drawable.character_focused);
                } else {
                    container.setBackgroundResource(R.drawable.character_not_focused_padding);
                }
            }
        });
        setFocusable(true);
    }

    public void updateUi(User card) {
        TextView primaryText = (TextView) findViewById(R.id.primary_text);
        final ImageView imageView = (ImageView) findViewById(R.id.main_image);
        primaryText.setTextColor(Color.WHITE);

        if (card != null) {
            primaryText.setText(card.getName());
            if (card.getImageUrl() != null && !card.getImageUrl().isEmpty()) {

                Glide.with(this)
                        .asBitmap()
                        .load(card.getImageUrl())
                        .placeholder(R.drawable.profile)
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getContext().getResources(), bitmap);
                                drawable.setAntiAlias(true);
                                drawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 5.5f);
                                imageView.setImageDrawable(drawable);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {}
                        });
            }else {
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.profile);
                RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getContext().getResources(), bitmap);
                drawable.setAntiAlias(true);
                drawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 5.5f);
                imageView.setImageDrawable(drawable);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(40, 40, 40, 40);
            }
        }
    }
}
