package com.mravel.catalog.utils.place;

import com.mravel.catalog.model.doc.PlaceDoc.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Images {
  private Images() {}

  public static Image img(String url, String caption, boolean cover, Integer sortOrder) {
    return Image.builder()
        .url(url)
        .caption(caption)
        .cover(cover)
        .sortOrder(sortOrder == null ? 0 : sortOrder)
        .build();
  }

  public static List<Image> withCover(Image cover, Image... rest) {
    List<Image> list = new ArrayList<>();
    list.add(cover);
    list.addAll(Arrays.asList(rest));
    return list;
  }
}