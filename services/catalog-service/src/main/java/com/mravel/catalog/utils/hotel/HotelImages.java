package com.mravel.catalog.utils.hotel;

import com.mravel.catalog.model.doc.HotelDoc.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class HotelImages {

  private HotelImages() {}

  public static Image img(String url, String captionVi, boolean cover, Integer sortOrder) {
    return img(url, captionVi, null, cover, sortOrder);
  }

  public static Image img(String url, String captionVi, String captionEn, boolean cover, Integer sortOrder) {
    return Image.builder()
        .url(url)
        .caption(Localized.of(captionVi, captionEn))
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
