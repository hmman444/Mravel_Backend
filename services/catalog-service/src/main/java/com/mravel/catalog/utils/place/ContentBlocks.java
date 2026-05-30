package com.mravel.catalog.utils.place;

import com.mravel.catalog.model.doc.PlaceDoc.ContentBlock;
import com.mravel.catalog.model.doc.PlaceDoc.Image;

import java.util.List;

public final class ContentBlocks {
  private ContentBlocks() {}

  public static ContentBlock heading(String vi) {
    return heading(vi, null);
  }

  public static ContentBlock heading(String vi, String en) {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.HEADING)
        .text(Localized.of(vi, en))
        .build();
  }

  public static ContentBlock paragraph(String vi) {
    return paragraph(vi, null);
  }

  public static ContentBlock paragraph(String vi, String en) {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.PARAGRAPH)
        .text(Localized.of(vi, en))
        .build();
  }

  public static ContentBlock quote(String vi) {
    return quote(vi, null);
  }

  public static ContentBlock quote(String vi, String en) {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.QUOTE)
        .text(Localized.of(vi, en))
        .build();
  }

  public static ContentBlock infoBox(String vi) {
    return infoBox(vi, null);
  }

  public static ContentBlock infoBox(String vi, String en) {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.INFOBOX)
        .text(Localized.of(vi, en))
        .build();
  }

  public static ContentBlock divider() {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.DIVIDER)
        .build();
  }

  public static ContentBlock mapBlock(double[] lonLat) {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.MAP)
        .mapLocation(lonLat)
        .build();
  }

  public static ContentBlock imageBlock(Image img) {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.IMAGE)
        .image(img)
        .build();
  }

  public static ContentBlock gallery(List<Image> imgs) {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.GALLERY)
        .gallery(imgs)
        .build();
  }
}
