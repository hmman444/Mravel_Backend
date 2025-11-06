package com.mravel.catalog.utils;

import com.mravel.catalog.model.doc.PlaceDoc.ContentBlock;
import com.mravel.catalog.model.doc.PlaceDoc.Image;

import java.util.List;

public final class ContentBlocks {
  private ContentBlocks() {}

  public static ContentBlock heading(String text) {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.HEADING)
        .text(text)
        .build();
  }

  public static ContentBlock paragraph(String text) {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.PARAGRAPH)
        .text(text)
        .build();
  }

  public static ContentBlock quote(String text) {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.QUOTE)
        .text(text)
        .build();
  }

  public static ContentBlock infoBox(String text) {
    return ContentBlock.builder()
        .type(ContentBlock.BlockType.INFOBOX)
        .text(text)
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