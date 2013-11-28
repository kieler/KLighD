/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering;

/**
 * An color library containing a huge collection named colors.<br>
 * The list of literals have been extracted from http://www.farb-tabelle.de/de/farbtabelle.htm.  
 * 
 * @author asr
 * @author chsch
 */
public enum Colors {

    /* shadings of black */

    /** black. */
    BLACK("black", 0, 0, 0),

    /* shadings of blue */

    /** AliceBlue. */
    ALICE_BLUE("AliceBlue", 240, 248, 255),

    /** BlueViolet. */
    BLUE_VIOLET("BlueViolet", 138, 43, 226),

    /** CadetBlue. */
    CADET_BLUE("CadetBlue", 95, 158, 160),

    /** CadetBlue1. */
    CADET_BLUE_1("CadetBlue1", 152, 245, 255),

    /** CadetBlue2. */
    CADET_BLUE_2("CadetBlue2", 142, 229, 238),

    /** CadetBlue3. */
    CADET_BLUE_3("CadetBlue3", 122, 197, 205),

    /** CadetBlue4. */
    CADET_BLUE_4("CadetBlue4", 83, 134, 139),

    /** CornflowerBlue. */
    CORNFLOWER_BLUE("CornflowerBlue", 100, 149, 237),

    /** DarkBlue. */
    DARK_BLUE("DarkBlue", 0, 0, 139),

    /** DarkCyan. */
    DARK_CYAN("DarkCyan", 0, 139, 139),

    /** DarkSlateBlue. */
    DARK_SLATE_BLUE("DarkSlateBlue", 72, 61, 139),

    /** DarkTurquoise. */
    DARK_TURQUOISE("DarkTurquoise", 0, 206, 209),

    /** DeepSkyBlue. */
    DEEP_SKY_BLUE("DeepSkyBlue", 0, 191, 255),

    /** DeepSkyBlue1. */
    DEEP_SKY_BLUE_1("DeepSkyBlue1", 0, 191, 255),

    /** DeepSkyBlue2. */
    DEEP_SKY_BLUE_2("DeepSkyBlue2", 0, 178, 238),

    /** DeepSkyBlue3. */
    DEEP_SKY_BLUE_3("DeepSkyBlue3", 0, 154, 205),

    /** DeepSkyBlue4. */
    DEEP_SKY_BLUE_4("DeepSkyBlue4", 0, 104, 139),

    /** DodgerBlue. */
    DODGER_BLUE("DodgerBlue", 30, 144, 255),

    /** DodgerBlue1. */
    DODGER_BLUE_1("DodgerBlue1", 30, 144, 255),

    /** DodgerBlue2. */
    DODGER_BLUE_2("DodgerBlue2", 28, 134, 238),

    /** DodgerBlue3. */
    DODGER_BLUE_3("DodgerBlue3", 24, 116, 205),

    /** DodgerBlue4. */
    DODGER_BLUE_4("DodgerBlue4", 16, 78, 139),

    /** LightBlue. */
    LIGHT_BLUE("LightBlue", 173, 216, 230),

    /** LightBlue1. */
    LIGHT_BLUE_1("LightBlue1", 191, 239, 255),

    /** LightBlue2. */
    LIGHT_BLUE_2("LightBlue2", 178, 223, 238),

    /** LightBlue3. */
    LIGHT_BLUE_3("LightBlue3", 154, 192, 205),

    /** LightBlue4. */
    LIGHT_BLUE_4("LightBlue4", 104, 131, 139),

    /** LightCyan. */
    LIGHT_CYAN("LightCyan", 224, 255, 255),

    /** LightCyan1. */
    LIGHT_CYAN_1("LightCyan1", 224, 255, 255),

    /** LightCyan2. */
    LIGHT_CYAN_2("LightCyan2", 209, 238, 238),

    /** LightCyan3. */
    LIGHT_CYAN_3("LightCyan3", 180, 205, 205),

    /** LightCyan4. */
    LIGHT_CYAN_4("LightCyan4", 122, 139, 139),

    /** LightSkyBlue. */
    LIGHT_SKY_BLUE("LightSkyBlue", 135, 206, 250),

    /** LightSkyBlue1. */
    LIGHT_SKY_BLUE_1("LightSkyBlue1", 176, 226, 255),

    /** LightSkyBlue2. */
    LIGHT_SKY_BLUE_2("LightSkyBlue2", 164, 211, 238),

    /** LightSkyBlue3. */
    LIGHT_SKY_BLUE_3("LightSkyBlue3", 141, 182, 205),

    /** LightSkyBlue4. */
    LIGHT_SKY_BLUE_4("LightSkyBlue4", 96, 123, 139),

    /** LightSlateBlue. */
    LIGHT_SLATE_BLUE("LightSlateBlue", 132, 112, 255),

    /** LightSteelBlue. */
    LIGHT_STEEL_BLUE("LightSteelBlue", 176, 196, 222),

    /** LightSteelBlue1. */
    LIGHT_STEEL_BLUE_1("LightSteelBlue1", 202, 225, 255),

    /** LightSteelBlue2. */
    LIGHT_STEEL_BLUE_2("LightSteelBlue2", 188, 210, 238),

    /** LightSteelBlue3. */
    LIGHT_STEEL_BLUE_3("LightSteelBlue3", 162, 181, 205),

    /** LightSteelBlue4. */
    LIGHT_STEEL_BLUE_4("LightSteelBlue4", 110, 123, 139),

    /** MediumAquamarine. */
    MEDIUM_AQUAMARINE("MediumAquamarine", 102, 205, 170),

    /** MediumBlue. */
    MEDIUM_BLUE("MediumBlue", 0, 0, 205),

    /** MediumSlateBlue. */
    MEDIUM_SLATE_BLUE("MediumSlateBlue", 123, 104, 238),

    /** MediumTurquoise. */
    MEDIUM_TURQUOISE("MediumTurquoise", 72, 209, 204),

    /** MidnightBlue. */
    MIDNIGHT_BLUE("MidnightBlue", 25, 25, 112),

    /** NavyBlue. */
    NAVY_BLUE("NavyBlue", 0, 0, 128),

    /** PaleTurquoise. */
    PALE_TURQUOISE("PaleTurquoise", 175, 238, 238),

    /** PaleTurquoise1. */
    PALE_TURQUOISE_1("PaleTurquoise1", 187, 255, 255),

    /** PaleTurquoise2. */
    PALE_TURQUOISE_2("PaleTurquoise2", 174, 238, 238),

    /** PaleTurquoise3. */
    PALE_TURQUOISE_3("PaleTurquoise3", 150, 205, 205),

    /** PaleTurquoise4. */
    PALE_TURQUOISE_4("PaleTurquoise4", 102, 139, 139),

    /** PowderBlue. */
    POWDER_BLUE("PowderBlue", 176, 224, 230),

    /** RoyalBlue. */
    ROYAL_BLUE("RoyalBlue", 65, 105, 225),

    /** RoyalBlue1. */
    ROYAL_BLUE_1("RoyalBlue1", 72, 118, 255),

    /** RoyalBlue2. */
    ROYAL_BLUE_2("RoyalBlue2", 67, 110, 238),

    /** RoyalBlue3. */
    ROYAL_BLUE_3("RoyalBlue3", 58, 95, 205),

    /** RoyalBlue4. */
    ROYAL_BLUE_4("RoyalBlue4", 39, 64, 139),

    /** SkyBlue. */
    SKY_BLUE("SkyBlue", 135, 206, 235),

    /** SkyBlue1. */
    SKY_BLUE_1("SkyBlue1", 135, 206, 255),

    /** SkyBlue2. */
    SKY_BLUE_2("SkyBlue2", 126, 192, 238),

    /** SkyBlue3. */
    SKY_BLUE_3("SkyBlue3", 108, 166, 205),

    /** SkyBlue4. */
    SKY_BLUE_4("SkyBlue4", 74, 112, 139),

    /** SlateBlue. */
    SLATE_BLUE("SlateBlue", 106, 90, 205),

    /** SlateBlue1. */
    SLATE_BLUE_1("SlateBlue1", 131, 111, 255),

    /** SlateBlue2. */
    SLATE_BLUE_2("SlateBlue2", 122, 103, 238),

    /** SlateBlue3. */
    SLATE_BLUE_3("SlateBlue3", 105, 89, 205),

    /** SlateBlue4. */
    SLATE_BLUE_4("SlateBlue4", 71, 60, 139),

    /** SteelBlue. */
    STEEL_BLUE("SteelBlue", 70, 130, 180),

    /** SteelBlue1. */
    STEEL_BLUE_1("SteelBlue1", 99, 184, 255),

    /** SteelBlue2. */
    STEEL_BLUE_2("SteelBlue2", 92, 172, 238),

    /** SteelBlue3. */
    STEEL_BLUE_3("SteelBlue3", 79, 148, 205),

    /** SteelBlue4. */
    STEEL_BLUE_4("SteelBlue4", 54, 100, 139),

    /** aquamarine. */
    AQUAMARINE("aquamarine", 127, 255, 212),

    /** aquamarine1. */
    AQUAMARINE_1("aquamarine1", 127, 255, 212),

    /** aquamarine2. */
    AQUAMARINE_2("aquamarine2", 118, 238, 198),

    /** aquamarine3. */
    AQUAMARINE_3("aquamarine3", 102, 205, 170),

    /** aquamarine4. */
    AQUAMARINE_4("aquamarine4", 69, 139, 116),

    /** azure. */
    AZURE("azure", 240, 255, 255),

    /** azure1. */
    AZURE_1("azure1", 240, 255, 255),

    /** azure2. */
    AZURE_2("azure2", 224, 238, 238),

    /** azure3. */
    AZURE_3("azure3", 193, 205, 205),

    /** azure4. */
    AZURE_4("azure4", 131, 139, 139),

    /** blue. */
    BLUE("blue", 0, 0, 255),

    /** blue1. */
    BLUE_1("blue1", 0, 0, 255),

    /** blue2. */
    BLUE_2("blue2", 0, 0, 238),

    /** blue3. */
    BLUE_3("blue3", 0, 0, 205),

    /** blue4. */
    BLUE_4("blue4", 0, 0, 139),

    /** cyan. */
    CYAN("cyan", 0, 255, 255),

    /** cyan1. */
    CYAN_1("cyan1", 0, 255, 255),

    /** cyan2. */
    CYAN_2("cyan2", 0, 238, 238),

    /** cyan3. */
    CYAN_3("cyan3", 0, 205, 205),

    /** cyan4. */
    CYAN_4("cyan4", 0, 139, 139),

    /** navy. */
    NAVY("navy", 0, 0, 128),

    /** turquoise. */
    TURQUOISE("turquoise", 64, 224, 208),

    /** turquoise1. */
    TURQUOISE_1("turquoise1", 0, 245, 255),

    /** turquoise2. */
    TURQUOISE_2("turquoise2", 0, 229, 238),

    /** turquoise3. */
    TURQUOISE_3("turquoise3", 0, 197, 205),

    /** turquoise4. */
    TURQUOISE_4("turquoise4", 0, 134, 139),

    /* shadings of brown */

    /** RosyBrown. */
    ROSY_BROWN("RosyBrown", 188, 143, 143),

    /** RosyBrown1. */
    ROSY_BROWN_1("RosyBrown1", 255, 193, 193),

    /** RosyBrown2. */
    ROSY_BROWN_2("RosyBrown2", 238, 180, 180),

    /** RosyBrown3. */
    ROSY_BROWN_3("RosyBrown3", 205, 155, 155),

    /** RosyBrown4. */
    ROSY_BROWN_4("RosyBrown4", 139, 105, 105),

    /** SaddleBrown. */
    SADDLE_BROWN("SaddleBrown", 139, 69, 19),

    /** SandyBrown. */
    SANDY_BROWN("SandyBrown", 244, 164, 96),

    /** beige. */
    BEIGE("beige", 245, 245, 220),

    /** brown. */
    BROWN("brown", 165, 42, 42),

    /** brown1. */
    BROWN_1("brown1", 255, 64, 64),

    /** brown2. */
    BROWN_2("brown2", 238, 59, 59),

    /** brown3. */
    BROWN_3("brown3", 205, 51, 51),

    /** brown4. */
    BROWN_4("brown4", 139, 35, 35),

    /** burlywood. */
    BURLYWOOD("burlywood", 222, 184, 135),

    /** burlywood1. */
    BURLYWOOD_1("burlywood1", 255, 211, 155),

    /** burlywood2. */
    BURLYWOOD_2("burlywood2", 238, 197, 145),

    /** burlywood3. */
    BURLYWOOD_3("burlywood3", 205, 170, 125),

    /** burlywood4. */
    BURLYWOOD_4("burlywood4", 139, 115, 85),

    /** chocolate. */
    CHOCOLATE("chocolate", 210, 105, 30),

    /** chocolate1. */
    CHOCOLATE_1("chocolate1", 255, 127, 36),

    /** chocolate2. */
    CHOCOLATE_2("chocolate2", 238, 118, 33),

    /** chocolate3. */
    CHOCOLATE_3("chocolate3", 205, 102, 29),

    /** chocolate4. */
    CHOCOLATE_4("chocolate4", 139, 69, 19),

    /** peru. */
    PERU("peru", 205, 133, 63),

    /** tan. */
    TAN("tan", 210, 180, 140),

    /** tan1. */
    TAN_1("tan1", 255, 165, 79),

    /** tan2. */
    TAN_2("tan2", 238, 154, 73),

    /** tan3. */
    TAN_3("tan3", 205, 133, 63),

    /** tan4. */
    TAN_4("tan4", 139, 90, 43),

    /* shadings of gray */

    /** DarkSlateGray. */
    DARK_SLATE_GRAY("DarkSlateGray", 47, 79, 79),

    /** DarkSlateGray1. */
    DARK_SLATE_GRAY_1("DarkSlateGray1", 151, 255, 255),

    /** DarkSlateGray2. */
    DARK_SLATE_GRAY_2("DarkSlateGray2", 141, 238, 238),

    /** DarkSlateGray3. */
    DARK_SLATE_GRAY_3("DarkSlateGray3", 121, 205, 205),

    /** DarkSlateGray4. */
    DARK_SLATE_GRAY_4("DarkSlateGray4", 82, 139, 139),

    /** DimGray. */
    DIM_GRAY("DimGray", 105, 105, 105),

    /** LightGray. */
    LIGHT_GRAY("LightGray", 211, 211, 211),

    /** LightSlateGray. */
    LIGHT_SLATE_GRAY("LightSlateGray", 119, 136, 153),

    /** SlateGray. */
    SLATE_GRAY("SlateGray", 112, 128, 144),

    /** SlateGray1. */
    SLATE_GRAY_1("SlateGray1", 198, 226, 255),

    /** SlateGray2. */
    SLATE_GRAY_2("SlateGray2", 185, 211, 238),

    /** SlateGray3. */
    SLATE_GRAY_3("SlateGray3", 159, 182, 205),

    /** SlateGray4. */
    SLATE_GRAY_4("SlateGray4", 108, 123, 139),

    /** gray. */
    GRAY("gray", 190, 190, 190),

    /** gray0. */
    GRAY_0("gray0", 0, 0, 0),

    /** gray1. */
    GRAY_1("gray1", 3, 3, 3),

    /** gray2. */
    GRAY_2("gray2", 5, 5, 5),

    /** gray3. */
    GRAY_3("gray3", 8, 8, 8),

    /** gray4. */
    GRAY_4("gray4", 10, 10, 10),

    /** gray5. */
    GRAY_5("gray5", 13, 13, 13),

    /** gray6. */
    GRAY_6("gray6", 15, 15, 15),

    /** gray7. */
    GRAY_7("gray7", 18, 18, 18),

    /** gray8. */
    GRAY_8("gray8", 20, 20, 20),

    /** gray9. */
    GRAY_9("gray9", 23, 23, 23),

    /** gray10. */
    GRAY_10("gray10", 26, 26, 26),

    /** gray11. */
    GRAY_11("gray11", 28, 28, 28),

    /** gray12. */
    GRAY_12("gray12", 31, 31, 31),

    /** gray13. */
    GRAY_13("gray13", 33, 33, 33),

    /** gray14. */
    GRAY_14("gray14", 36, 36, 36),

    /** gray15. */
    GRAY_15("gray15", 38, 38, 38),

    /** gray16. */
    GRAY_16("gray16", 41, 41, 41),

    /** gray17. */
    GRAY_17("gray17", 43, 43, 43),

    /** gray18. */
    GRAY_18("gray18", 46, 46, 46),

    /** gray19. */
    GRAY_19("gray19", 48, 48, 48),

    /** gray20. */
    GRAY_20("gray20", 51, 51, 51),

    /** gray21. */
    GRAY_21("gray21", 54, 54, 54),

    /** gray22. */
    GRAY_22("gray22", 56, 56, 56),

    /** gray23. */
    GRAY_23("gray23", 59, 59, 59),

    /** gray24. */
    GRAY_24("gray24", 61, 61, 61),

    /** gray25. */
    GRAY_25("gray25", 64, 64, 64),

    /** gray26. */
    GRAY_26("gray26", 66, 66, 66),

    /** gray27. */
    GRAY_27("gray27", 69, 69, 69),

    /** gray28. */
    GRAY_28("gray28", 71, 71, 71),

    /** gray29. */
    GRAY_29("gray29", 74, 74, 74),

    /** gray30. */
    GRAY_30("gray30", 77, 77, 77),

    /** gray31. */
    GRAY_31("gray31", 79, 79, 79),

    /** gray32. */
    GRAY_32("gray32", 82, 82, 82),

    /** gray33. */
    GRAY_33("gray33", 84, 84, 84),

    /** gray34. */
    GRAY_34("gray34", 87, 87, 87),

    /** gray35. */
    GRAY_35("gray35", 89, 89, 89),

    /** gray36. */
    GRAY_36("gray36", 92, 92, 92),

    /** gray37. */
    GRAY_37("gray37", 94, 94, 94),

    /** gray38. */
    GRAY_38("gray38", 97, 97, 97),

    /** gray39. */
    GRAY_39("gray39", 99, 99, 99),

    /** gray40. */
    GRAY_40("gray40", 102, 102, 102),

    /** gray41. */
    GRAY_41("gray41", 105, 105, 105),

    /** gray42. */
    GRAY_42("gray42", 107, 107, 107),

    /** gray43. */
    GRAY_43("gray43", 110, 110, 110),

    /** gray44. */
    GRAY_44("gray44", 112, 112, 112),

    /** gray45. */
    GRAY_45("gray45", 115, 115, 115),

    /** gray46. */
    GRAY_46("gray46", 117, 117, 117),

    /** gray47. */
    GRAY_47("gray47", 120, 120, 120),

    /** gray48. */
    GRAY_48("gray48", 122, 122, 122),

    /** gray49. */
    GRAY_49("gray49", 125, 125, 125),

    /** gray50. */
    GRAY_50("gray50", 127, 127, 127),

    /** gray51. */
    GRAY_51("gray51", 130, 130, 130),

    /** gray52. */
    GRAY_52("gray52", 133, 133, 133),

    /** gray53. */
    GRAY_53("gray53", 135, 135, 135),

    /** gray54. */
    GRAY_54("gray54", 138, 138, 138),

    /** gray55. */
    GRAY_55("gray55", 140, 140, 140),

    /** gray56. */
    GRAY_56("gray56", 143, 143, 143),

    /** gray57. */
    GRAY_57("gray57", 145, 145, 145),

    /** gray58. */
    GRAY_58("gray58", 148, 148, 148),

    /** gray59. */
    GRAY_59("gray59", 150, 150, 150),

    /** gray60. */
    GRAY_60("gray60", 153, 153, 153),

    /** gray61. */
    GRAY_61("gray61", 156, 156, 156),

    /** gray62. */
    GRAY_62("gray62", 158, 158, 158),

    /** gray63. */
    GRAY_63("gray63", 161, 161, 161),

    /** gray64. */
    GRAY_64("gray64", 163, 163, 163),

    /** gray65. */
    GRAY_65("gray65", 166, 166, 166),

    /** gray66. */
    GRAY_66("gray66", 168, 168, 168),

    /** gray67. */
    GRAY_67("gray67", 171, 171, 171),

    /** gray68. */
    GRAY_68("gray68", 173, 173, 173),

    /** gray69. */
    GRAY_69("gray69", 176, 176, 176),

    /** gray70. */
    GRAY_70("gray70", 179, 179, 179),

    /** gray71. */
    GRAY_71("gray71", 181, 181, 181),

    /** gray72. */
    GRAY_72("gray72", 184, 184, 184),

    /** gray73. */
    GRAY_73("gray73", 186, 186, 186),

    /** gray74. */
    GRAY_74("gray74", 189, 189, 189),

    /** gray75. */
    GRAY_75("gray75", 191, 191, 191),

    /** gray76. */
    GRAY_76("gray76", 194, 194, 194),

    /** gray77. */
    GRAY_77("gray77", 196, 196, 196),

    /** gray78. */
    GRAY_78("gray78", 199, 199, 199),

    /** gray79. */
    GRAY_79("gray79", 201, 201, 201),

    /** gray80. */
    GRAY_80("gray80", 204, 204, 204),

    /** gray81. */
    GRAY_81("gray81", 207, 207, 207),

    /** gray82. */
    GRAY_82("gray82", 209, 209, 209),

    /** gray83. */
    GRAY_83("gray83", 212, 212, 212),

    /** gray84. */
    GRAY_84("gray84", 214, 214, 214),

    /** gray85. */
    GRAY_85("gray85", 217, 217, 217),

    /** gray86. */
    GRAY_86("gray86", 219, 219, 219),

    /** gray87. */
    GRAY_87("gray87", 222, 222, 222),

    /** gray88. */
    GRAY_88("gray88", 224, 224, 224),

    /** gray89. */
    GRAY_89("gray89", 227, 227, 227),

    /** gray90. */
    GRAY_90("gray90", 229, 229, 229),

    /** gray91. */
    GRAY_91("gray91", 232, 232, 232),

    /** gray92. */
    GRAY_92("gray92", 235, 235, 235),

    /** gray93. */
    GRAY_93("gray93", 237, 237, 237),

    /** gray94. */
    GRAY_94("gray94", 240, 240, 240),

    /** gray95. */
    GRAY_95("gray95", 242, 242, 242),

    /** gray96. */
    GRAY_96("gray96", 245, 245, 245),

    /** gray97. */
    GRAY_97("gray97", 247, 247, 247),

    /** gray98. */
    GRAY_98("gray98", 250, 250, 250),

    /** gray99. */
    GRAY_99("gray99", 252, 252, 252),

    /** gray100. */
    GRAY_100("gray100", 255, 255, 255),

    /* shadings of green */

    /** DarkGreen. */
    DARK_GREEN("DarkGreen", 0, 100, 0),

    /** DarkKhaki. */
    DARK_KHAKI("DarkKhaki", 189, 183, 107),

    /** DarkOliveGreen. */
    DARK_OLIVE_GREEN("DarkOliveGreen", 85, 107, 47),

    /** DarkOliveGreen1. */
    DARK_OLIVE_GREEN_1("DarkOliveGreen1", 202, 255, 112),

    /** DarkOliveGreen2. */
    DARK_OLIVE_GREEN_2("DarkOliveGreen2", 188, 238, 104),

    /** DarkOliveGreen3. */
    DARK_OLIVE_GREEN_3("DarkOliveGreen3", 162, 205, 90),

    /** DarkOliveGreen4. */
    DARK_OLIVE_GREEN_4("DarkOliveGreen4", 110, 139, 61),

    /** DarkSeaGreen. */
    DARK_SEA_GREEN("DarkSeaGreen", 143, 188, 143),

    /** DarkSeaGreen1. */
    DARK_SEA_GREEN_1("DarkSeaGreen1", 193, 255, 193),

    /** DarkSeaGreen2. */
    DARK_SEA_GREEN_2("DarkSeaGreen2", 180, 238, 180),

    /** DarkSeaGreen3. */
    DARK_SEA_GREEN_3("DarkSeaGreen3", 155, 205, 155),

    /** DarkSeaGreen4. */
    DARK_SEA_GREEN_4("DarkSeaGreen4", 105, 139, 105),

    /** ForestGreen. */
    FOREST_GREEN("ForestGreen", 34, 139, 34),

    /** GreenYellow. */
    GREEN_YELLOW("GreenYellow", 173, 255, 47),

    /** LawnGreen. */
    LAWN_GREEN("LawnGreen", 124, 252, 0),

    /** LightGreen. */
    LIGHT_GREEN("LightGreen", 144, 238, 144),

    /** LightSeaGreen. */
    LIGHT_SEA_GREEN("LightSeaGreen", 32, 178, 170),

    /** LimeGreen. */
    LIME_GREEN("LimeGreen", 50, 205, 50),

    /** MediumSeaGreen. */
    MEDIUM_SEA_GREEN("MediumSeaGreen", 60, 179, 113),

    /** MediumSpringGreen. */
    MEDIUM_SPRING_GREEN("MediumSpringGreen", 0, 250, 154),

    /** MintCream. */
    MINT_CREAM("MintCream", 245, 255, 250),

    /** OliveDrab. */
    OLIVE_DRAB("OliveDrab", 107, 142, 35),

    /** OliveDrab1. */
    OLIVE_DRAB_1("OliveDrab1", 192, 255, 62),

    /** OliveDrab2. */
    OLIVE_DRAB_2("OliveDrab2", 179, 238, 58),

    /** OliveDrab3. */
    OLIVE_DRAB_3("OliveDrab3", 154, 205, 50),

    /** OliveDrab4. */
    OLIVE_DRAB_4("OliveDrab4", 105, 139, 34),

    /** PaleGreen. */
    PALE_GREEN("PaleGreen", 152, 251, 152),

    /** PaleGreen1. */
    PALE_GREEN_1("PaleGreen1", 154, 255, 154),

    /** PaleGreen2. */
    PALE_GREEN_2("PaleGreen2", 144, 238, 144),

    /** PaleGreen3. */
    PALE_GREEN_3("PaleGreen3", 124, 205, 124),

    /** PaleGreen4. */
    PALE_GREEN_4("PaleGreen4", 84, 139, 84),

    /** SeaGreen. */
    SEA_GREEN("SeaGreen", 46, 139, 87),

    /** SeaGreen1. */
    SEA_GREEN_1("SeaGreen1", 84, 255, 159),

    /** SeaGreen2. */
    SEA_GREEN_2("SeaGreen2", 78, 238, 148),

    /** SeaGreen3. */
    SEA_GREEN_3("SeaGreen3", 67, 205, 128),

    /** SeaGreen4. */
    SEA_GREEN_4("SeaGreen4", 46, 139, 87),

    /** SpringGreen. */
    SPRING_GREEN("SpringGreen", 0, 255, 127),

    /** SpringGreen1. */
    SPRING_GREEN_1("SpringGreen1", 0, 255, 127),

    /** SpringGreen2. */
    SPRING_GREEN_2("SpringGreen2", 0, 238, 118),

    /** SpringGreen3. */
    SPRING_GREEN_3("SpringGreen3", 0, 205, 102),

    /** SpringGreen4. */
    SPRING_GREEN_4("SpringGreen4", 0, 139, 69),

    /** YellowGreen. */
    YELLOW_GREEN("YellowGreen", 154, 205, 50),

    /** chartreuse. */
    CHARTREUSE("chartreuse", 127, 255, 0),

    /** chartreuse1. */
    CHARTREUSE_1("chartreuse1", 127, 255, 0),

    /** chartreuse2. */
    CHARTREUSE_2("chartreuse2", 118, 238, 0),

    /** chartreuse3. */
    CHARTREUSE_3("chartreuse3", 102, 205, 0),

    /** chartreuse4. */
    CHARTREUSE_4("chartreuse4", 69, 139, 0),

    /** green. */
    GREEN("green", 0, 255, 0),

    /** green1. */
    GREEN_1("green1", 0, 255, 0),

    /** green2. */
    GREEN_2("green2", 0, 238, 0),

    /** green3. */
    GREEN_3("green3", 0, 205, 0),

    /** green4. */
    GREEN_4("green4", 0, 139, 0),

    /** khaki. */
    KHAKI("khaki", 240, 230, 140),

    /** khaki1. */
    KHAKI_1("khaki1", 255, 246, 143),

    /** khaki2. */
    KHAKI_2("khaki2", 238, 230, 133),

    /** khaki3. */
    KHAKI_3("khaki3", 205, 198, 115),

    /** khaki4. */
    KHAKI_4("khaki4", 139, 134, 78),

    /* shadings of orange */

    /** DarkOrange. */
    DARK_ORANGE("DarkOrange", 255, 140, 0),

    /** DarkOrange1. */
    DARK_ORANGE_1("DarkOrange1", 255, 127, 0),

    /** DarkOrange2. */
    DARK_ORANGE_2("DarkOrange2", 238, 118, 0),

    /** DarkOrange3. */
    DARK_ORANGE_3("DarkOrange3", 205, 102, 0),

    /** DarkOrange4. */
    DARK_ORANGE_4("DarkOrange4", 139, 69, 0),

    /** DarkSalmon. */
    DARK_SALMON("DarkSalmon", 233, 150, 122),

    /** LightCoral. */
    LIGHT_CORAL("LightCoral", 240, 128, 128),

    /** LightSalmon. */
    LIGHT_SALMON("LightSalmon", 255, 160, 122),

    /** LightSalmon1. */
    LIGHT_SALMON_1("LightSalmon1", 255, 160, 122),

    /** LightSalmon2. */
    LIGHT_SALMON_2("LightSalmon2", 238, 149, 114),

    /** LightSalmon3. */
    LIGHT_SALMON_3("LightSalmon3", 205, 129, 98),

    /** LightSalmon4. */
    LIGHT_SALMON_4("LightSalmon4", 139, 87, 66),

    /** PeachPuff. */
    PEACH_PUFF("PeachPuff", 255, 218, 185),

    /** PeachPuff1. */
    PEACH_PUFF_1("PeachPuff1", 255, 218, 185),

    /** PeachPuff2. */
    PEACH_PUFF_2("PeachPuff2", 238, 203, 173),

    /** PeachPuff3. */
    PEACH_PUFF_3("PeachPuff3", 205, 175, 149),

    /** PeachPuff4. */
    PEACH_PUFF_4("PeachPuff4", 139, 119, 101),

    /** bisque. */
    BISQUE("bisque", 255, 228, 196),

    /** bisque1. */
    BISQUE_1("bisque1", 255, 228, 196),

    /** bisque2. */
    BISQUE_2("bisque2", 238, 213, 183),

    /** bisque3. */
    BISQUE_3("bisque3", 205, 183, 158),

    /** bisque4. */
    BISQUE_4("bisque4", 139, 125, 107),

    /** coral. */
    CORAL("coral", 255, 127, 80),

    /** coral1. */
    CORAL_1("coral1", 255, 114, 86),

    /** coral2. */
    CORAL_2("coral2", 238, 106, 80),

    /** coral3. */
    CORAL_3("coral3", 205, 91, 69),

    /** coral4. */
    CORAL_4("coral4", 139, 62, 47),

    /** honeydew. */
    HONEYDEW("honeydew", 240, 255, 240),

    /** honeydew1. */
    HONEYDEW_1("honeydew1", 240, 255, 240),

    /** honeydew2. */
    HONEYDEW_2("honeydew2", 224, 238, 224),

    /** honeydew3. */
    HONEYDEW_3("honeydew3", 193, 205, 193),

    /** honeydew4. */
    HONEYDEW_4("honeydew4", 131, 139, 131),

    /** orange. */
    ORANGE("orange", 255, 165, 0),

    /** orange1. */
    ORANGE_1("orange1", 255, 165, 0),

    /** orange2. */
    ORANGE_2("orange2", 238, 154, 0),

    /** orange3. */
    ORANGE_3("orange3", 205, 133, 0),

    /** orange4. */
    ORANGE_4("orange4", 139, 90, 0),

    /** salmon. */
    SALMON("salmon", 250, 128, 114),

    /** salmon1. */
    SALMON_1("salmon1", 255, 140, 105),

    /** salmon2. */
    SALMON_2("salmon2", 238, 130, 98),

    /** salmon3. */
    SALMON_3("salmon3", 205, 112, 84),

    /** salmon4. */
    SALMON_4("salmon4", 139, 76, 57),

    /** sienna. */
    SIENNA("sienna", 160, 82, 45),

    /** sienna1. */
    SIENNA_1("sienna1", 255, 130, 71),

    /** sienna2. */
    SIENNA_2("sienna2", 238, 121, 66),

    /** sienna3. */
    SIENNA_3("sienna3", 205, 104, 57),

    /** sienna4. */
    SIENNA_4("sienna4", 139, 71, 38),

    /* shadings of red */

    /** DarkRed. */
    DARK_RED("DarkRed", 139, 0, 0),

    /** DeepPink. */
    DEEP_PINK("DeepPink", 255, 20, 147),

    /** DeepPink1. */
    DEEP_PINK_1("DeepPink1", 255, 20, 147),

    /** DeepPink2. */
    DEEP_PINK_2("DeepPink2", 238, 18, 137),

    /** DeepPink3. */
    DEEP_PINK_3("DeepPink3", 205, 16, 118),

    /** DeepPink4. */
    DEEP_PINK_4("DeepPink4", 139, 10, 80),

    /** HotPink. */
    HOT_PINK("HotPink", 255, 105, 180),

    /** HotPink1. */
    HOT_PINK_1("HotPink1", 255, 110, 180),

    /** HotPink2. */
    HOT_PINK_2("HotPink2", 238, 106, 167),

    /** HotPink3. */
    HOT_PINK_3("HotPink3", 205, 96, 144),

    /** HotPink4. */
    HOT_PINK_4("HotPink4", 139, 58, 98),

    /** IndianRed. */
    INDIAN_RED("IndianRed", 205, 92, 92),

    /** IndianRed1. */
    INDIAN_RED_1("IndianRed1", 255, 106, 106),

    /** IndianRed2. */
    INDIAN_RED_2("IndianRed2", 238, 99, 99),

    /** IndianRed3. */
    INDIAN_RED_3("IndianRed3", 205, 85, 85),

    /** IndianRed4. */
    INDIAN_RED_4("IndianRed4", 139, 58, 58),

    /** LightPink. */
    LIGHT_PINK("LightPink", 255, 182, 193),

    /** LightPink1. */
    LIGHT_PINK_1("LightPink1", 255, 174, 185),

    /** LightPink2. */
    LIGHT_PINK_2("LightPink2", 238, 162, 173),

    /** LightPink3. */
    LIGHT_PINK_3("LightPink3", 205, 140, 149),

    /** LightPink4. */
    LIGHT_PINK_4("LightPink4", 139, 95, 101),

    /** MediumVioletRed. */
    MEDIUM_VIOLET_RED("MediumVioletRed", 199, 21, 133),

    /** MistyRose. */
    MISTY_ROSE("MistyRose", 255, 228, 225),

    /** MistyRose1. */
    MISTY_ROSE_1("MistyRose1", 255, 228, 225),

    /** MistyRose2. */
    MISTY_ROSE_2("MistyRose2", 238, 213, 210),

    /** MistyRose3. */
    MISTY_ROSE_3("MistyRose3", 205, 183, 181),

    /** MistyRose4. */
    MISTY_ROSE_4("MistyRose4", 139, 125, 123),

    /** OrangeRed. */
    ORANGE_RED("OrangeRed", 255, 69, 0),

    /** OrangeRed1. */
    ORANGE_RED_1("OrangeRed1", 255, 69, 0),

    /** OrangeRed2. */
    ORANGE_RED_2("OrangeRed2", 238, 64, 0),

    /** OrangeRed3. */
    ORANGE_RED_3("OrangeRed3", 205, 55, 0),

    /** OrangeRed4. */
    ORANGE_RED_4("OrangeRed4", 139, 37, 0),

    /** PaleVioletRed. */
    PALE_VIOLET_RED("PaleVioletRed", 219, 112, 147),

    /** PaleVioletRed1. */
    PALE_VIOLET_RED_1("PaleVioletRed1", 255, 130, 171),

    /** PaleVioletRed2. */
    PALE_VIOLET_RED_2("PaleVioletRed2", 238, 121, 159),

    /** PaleVioletRed3. */
    PALE_VIOLET_RED_3("PaleVioletRed3", 205, 104, 137),

    /** PaleVioletRed4. */
    PALE_VIOLET_RED_4("PaleVioletRed4", 139, 71, 93),

    /** VioletRed. */
    VIOLET_RED("VioletRed", 208, 32, 144),

    /** VioletRed1. */
    VIOLET_RED_1("VioletRed1", 255, 62, 150),

    /** VioletRed2. */
    VIOLET_RED_2("VioletRed2", 238, 58, 140),

    /** VioletRed3. */
    VIOLET_RED_3("VioletRed3", 205, 50, 120),

    /** VioletRed4. */
    VIOLET_RED_4("VioletRed4", 139, 34, 82),

    /** firebrick. */
    FIREBRICK("firebrick", 178, 34, 34),

    /** firebrick1. */
    FIREBRICK_1("firebrick1", 255, 48, 48),

    /** firebrick2. */
    FIREBRICK_2("firebrick2", 238, 44, 44),

    /** firebrick3. */
    FIREBRICK_3("firebrick3", 205, 38, 38),

    /** firebrick4. */
    FIREBRICK_4("firebrick4", 139, 26, 26),

    /** pink. */
    PINK("pink", 255, 192, 203),

    /** pink1. */
    PINK_1("pink1", 255, 181, 197),

    /** pink2. */
    PINK_2("pink2", 238, 169, 184),

    /** pink3. */
    PINK_3("pink3", 205, 145, 158),

    /** pink4. */
    PINK_4("pink4", 139, 99, 108),

    /** red. */
    RED("red", 255, 0, 0),

    /** red1. */
    RED_1("red1", 255, 0, 0),

    /** red2. */
    RED_2("red2", 238, 0, 0),

    /** red3. */
    RED_3("red3", 205, 0, 0),

    /** red4. */
    RED_4("red4", 139, 0, 0),

    /** tomato. */
    TOMATO("tomato", 255, 99, 71),

    /** tomato1. */
    TOMATO_1("tomato1", 255, 99, 71),

    /** tomato2. */
    TOMATO_2("tomato2", 238, 92, 66),

    /** tomato3. */
    TOMATO_3("tomato3", 205, 79, 57),

    /** tomato4. */
    TOMATO_4("tomato4", 139, 54, 38),

    /* shadings of violet */

    /** DarkMagenta. */
    DARK_MAGENTA("DarkMagenta", 139, 0, 139),

    /** DarkOrchid. */
    DARK_ORCHID("DarkOrchid", 153, 50, 204),

    /** DarkOrchid1. */
    DARK_ORCHID_1("DarkOrchid1", 191, 62, 255),

    /** DarkOrchid2. */
    DARK_ORCHID_2("DarkOrchid2", 178, 58, 238),

    /** DarkOrchid3. */
    DARK_ORCHID_3("DarkOrchid3", 154, 50, 205),

    /** DarkOrchid4. */
    DARK_ORCHID_4("DarkOrchid4", 104, 34, 139),

    /** DarkViolet. */
    DARK_VIOLET("DarkViolet", 148, 0, 211),

    /** LavenderBlush. */
    LAVENDER_BLUSH("LavenderBlush", 255, 240, 245),

    /** LavenderBlush1. */
    LAVENDER_BLUSH_1("LavenderBlush1", 255, 240, 245),

    /** LavenderBlush2. */
    LAVENDER_BLUSH_2("LavenderBlush2", 238, 224, 229),

    /** LavenderBlush3. */
    LAVENDER_BLUSH_3("LavenderBlush3", 205, 193, 197),

    /** LavenderBlush4. */
    LAVENDER_BLUSH_4("LavenderBlush4", 139, 131, 134),

    /** MediumOrchid. */
    MEDIUM_ORCHID("MediumOrchid", 186, 85, 211),

    /** MediumOrchid1. */
    MEDIUM_ORCHID_1("MediumOrchid1", 224, 102, 255),

    /** MediumOrchid2. */
    MEDIUM_ORCHID_2("MediumOrchid2", 209, 95, 238),

    /** MediumOrchid3. */
    MEDIUM_ORCHID_3("MediumOrchid3", 180, 82, 205),

    /** MediumOrchid4. */
    MEDIUM_ORCHID_4("MediumOrchid4", 122, 55, 139),

    /** MediumPurple. */
    MEDIUM_PURPLE("MediumPurple", 147, 112, 219),

    /** MediumPurple1. */
    MEDIUM_PURPLE_1("MediumPurple1", 171, 130, 255),

    /** MediumPurple2. */
    MEDIUM_PURPLE_2("MediumPurple2", 159, 121, 238),

    /** MediumPurple3. */
    MEDIUM_PURPLE_3("MediumPurple3", 137, 104, 205),

    /** MediumPurple4. */
    MEDIUM_PURPLE_4("MediumPurple4", 93, 71, 139),

    /** lavender. */
    LAVENDER("lavender", 230, 230, 250),

    /** magenta. */
    MAGENTA("magenta", 255, 0, 255),

    /** magenta1. */
    MAGENTA_1("magenta1", 255, 0, 255),

    /** magenta2. */
    MAGENTA_2("magenta2", 238, 0, 238),

    /** magenta3. */
    MAGENTA_3("magenta3", 205, 0, 205),

    /** magenta4. */
    MAGENTA_4("magenta4", 139, 0, 139),

    /** maroon. */
    MAROON("maroon", 176, 48, 96),

    /** maroon1. */
    MAROON_1("maroon1", 255, 52, 179),

    /** maroon2. */
    MAROON_2("maroon2", 238, 48, 167),

    /** maroon3. */
    MAROON_3("maroon3", 205, 41, 144),

    /** maroon4. */
    MAROON_4("maroon4", 139, 28, 98),

    /** orchid. */
    ORCHID("orchid", 218, 112, 214),

    /** orchid1. */
    ORCHID_1("orchid1", 255, 131, 250),

    /** orchid2. */
    ORCHID_2("orchid2", 238, 122, 233),

    /** orchid3. */
    ORCHID_3("orchid3", 205, 105, 201),

    /** orchid4. */
    ORCHID_4("orchid4", 139, 71, 137),

    /** plum. */
    PLUM("plum", 221, 160, 221),

    /** plum1. */
    PLUM_1("plum1", 255, 187, 255),

    /** plum2. */
    PLUM_2("plum2", 238, 174, 238),

    /** plum3. */
    PLUM_3("plum3", 205, 150, 205),

    /** plum4. */
    PLUM_4("plum4", 139, 102, 139),

    /** purple. */
    PURPLE("purple", 160, 32, 240),

    /** purple1. */
    PURPLE_1("purple1", 155, 48, 255),

    /** purple2. */
    PURPLE_2("purple2", 145, 44, 238),

    /** purple3. */
    PURPLE_3("purple3", 125, 38, 205),

    /** purple4. */
    PURPLE_4("purple4", 85, 26, 139),

    /** thistle. */
    THISTLE("thistle", 216, 191, 216),

    /** thistle1. */
    THISTLE_1("thistle1", 255, 225, 255),

    /** thistle2. */
    THISTLE_2("thistle2", 238, 210, 238),

    /** thistle3. */
    THISTLE_3("thistle3", 205, 181, 205),

    /** thistle4. */
    THISTLE_4("thistle4", 139, 123, 139),

    /** violet. */
    VIOLET("violet", 238, 130, 238),

    /* shadings of white */

    /** AntiqueWhite. */
    ANTIQUE_WHITE("AntiqueWhite", 250, 235, 215),

    /** AntiqueWhite1. */
    ANTIQUE_WHITE_1("AntiqueWhite1", 255, 239, 219),

    /** AntiqueWhite2. */
    ANTIQUE_WHITE_2("AntiqueWhite2", 238, 223, 204),

    /** AntiqueWhite3. */
    ANTIQUE_WHITE_3("AntiqueWhite3", 205, 192, 176),

    /** AntiqueWhite4. */
    ANTIQUE_WHITE_4("AntiqueWhite4", 139, 131, 120),

    /** FloralWhite. */
    FLORAL_WHITE("FloralWhite", 255, 250, 240),

    /** GhostWhite. */
    GHOST_WHITE("GhostWhite", 248, 248, 255),

    /** NavajoWhite. */
    NAVAJO_WHITE("NavajoWhite", 255, 222, 173),

    /** NavajoWhite1. */
    NAVAJO_WHITE_1("NavajoWhite1", 255, 222, 173),

    /** NavajoWhite2. */
    NAVAJO_WHITE_2("NavajoWhite2", 238, 207, 161),

    /** NavajoWhite3. */
    NAVAJO_WHITE_3("NavajoWhite3", 205, 179, 139),

    /** NavajoWhite4. */
    NAVAJO_WHITE_4("NavajoWhite4", 139, 121, 94),

    /** OldLace. */
    OLD_LACE("OldLace", 253, 245, 230),

    /** WhiteSmoke. */
    WHITE_SMOKE("WhiteSmoke", 245, 245, 245),

    /** gainsboro. */
    GAINSBORO("gainsboro", 220, 220, 220),

    /** ivory. */
    IVORY("ivory", 255, 255, 240),

    /** ivory1. */
    IVORY_1("ivory1", 255, 255, 240),

    /** ivory2. */
    IVORY_2("ivory2", 238, 238, 224),

    /** ivory3. */
    IVORY_3("ivory3", 205, 205, 193),

    /** ivory4. */
    IVORY_4("ivory4", 139, 139, 131),

    /** linen. */
    LINEN("linen", 250, 240, 230),

    /** seashell. */
    SEASHELL("seashell", 255, 245, 238),

    /** seashell1. */
    SEASHELL_1("seashell1", 255, 245, 238),

    /** seashell2. */
    SEASHELL_2("seashell2", 238, 229, 222),

    /** seashell3. */
    SEASHELL_3("seashell3", 205, 197, 191),

    /** seashell4. */
    SEASHELL_4("seashell4", 139, 134, 130),

    /** snow. */
    SNOW("snow", 255, 250, 250),

    /** snow1. */
    SNOW_1("snow1", 255, 250, 250),

    /** snow2. */
    SNOW_2("snow2", 238, 233, 233),

    /** snow3. */
    SNOW_3("snow3", 205, 201, 201),

    /** snow4. */
    SNOW_4("snow4", 139, 137, 137),

    /** wheat. */
    WHEAT("wheat", 245, 222, 179),

    /** wheat1. */
    WHEAT_1("wheat1", 255, 231, 186),

    /** wheat2. */
    WHEAT_2("wheat2", 238, 216, 174),

    /** wheat3. */
    WHEAT_3("wheat3", 205, 186, 150),

    /** wheat4. */
    WHEAT_4("wheat4", 139, 126, 102),

    /** white. */
    WHITE("white", 255, 255, 255),

    /* shadings of yellow */

    /** BlanchedAlmond. */
    BLANCHED_ALMOND("BlanchedAlmond", 255, 235, 205),

    /** DarkGoldenrod. */
    DARK_GOLDENROD("DarkGoldenrod", 184, 134, 11),

    /** DarkGoldenrod1. */
    DARK_GOLDENROD_1("DarkGoldenrod1", 255, 185, 15),

    /** DarkGoldenrod2. */
    DARK_GOLDENROD_2("DarkGoldenrod2", 238, 173, 14),

    /** DarkGoldenrod3. */
    DARK_GOLDENROD_3("DarkGoldenrod3", 205, 149, 12),

    /** DarkGoldenrod4. */
    DARK_GOLDENROD_4("DarkGoldenrod4", 139, 101, 8),

    /** LemonChiffon. */
    LEMON_CHIFFON("LemonChiffon", 255, 250, 205),

    /** LemonChiffon1. */
    LEMON_CHIFFON_1("LemonChiffon1", 255, 250, 205),

    /** LemonChiffon2. */
    LEMON_CHIFFON_2("LemonChiffon2", 238, 233, 191),

    /** LemonChiffon3. */
    LEMON_CHIFFON_3("LemonChiffon3", 205, 201, 165),

    /** LemonChiffon4. */
    LEMON_CHIFFON_4("LemonChiffon4", 139, 137, 112),

    /** LightGoldenrod. */
    LIGHT_GOLDENROD("LightGoldenrod", 238, 221, 130),

    /** LightGoldenrod1. */
    LIGHT_GOLDENROD_1("LightGoldenrod1", 255, 236, 139),

    /** LightGoldenrod2. */
    LIGHT_GOLDENROD_2("LightGoldenrod2", 238, 220, 130),

    /** LightGoldenrod3. */
    LIGHT_GOLDENROD_3("LightGoldenrod3", 205, 190, 112),

    /** LightGoldenrod4. */
    LIGHT_GOLDENROD_4("LightGoldenrod4", 139, 129, 76),

    /** LightGoldenrodYellow. */
    LIGHT_GOLDENROD_YELLOW("LightGoldenrodYellow", 250, 250, 210),

    /** LightYellow. */
    LIGHT_YELLOW("LightYellow", 255, 255, 224),

    /** LightYellow1. */
    LIGHT_YELLOW_1("LightYellow1", 255, 255, 224),

    /** LightYellow2. */
    LIGHT_YELLOW_2("LightYellow2", 238, 238, 209),

    /** LightYellow3. */
    LIGHT_YELLOW_3("LightYellow3", 205, 205, 180),

    /** LightYellow4. */
    LIGHT_YELLOW_4("LightYellow4", 139, 139, 122),

    /** PaleGoldenrod. */
    PALE_GOLDENROD("PaleGoldenrod", 238, 232, 170),

    /** PapayaWhip. */
    PAPAYA_WHIP("PapayaWhip", 255, 239, 213),

    /** cornsilk. */
    CORNSILK("cornsilk", 255, 248, 220),

    /** cornsilk1. */
    CORNSILK_1("cornsilk1", 255, 248, 220),

    /** cornsilk2. */
    CORNSILK_2("cornsilk2", 238, 232, 205),

    /** cornsilk3. */
    CORNSILK_3("cornsilk3", 205, 200, 177),

    /** cornsilk4. */
    CORNSILK_4("cornsilk4", 139, 136, 120),

    /** gold. */
    GOLD("gold", 255, 215, 0),

    /** gold1. */
    GOLD_1("gold1", 255, 215, 0),

    /** gold2. */
    GOLD_2("gold2", 238, 201, 0),

    /** gold3. */
    GOLD_3("gold3", 205, 173, 0),

    /** gold4. */
    GOLD_4("gold4", 139, 117, 0),

    /** goldenrod. */
    GOLDENROD("goldenrod", 218, 165, 32),

    /** goldenrod1. */
    GOLDENROD_1("goldenrod1", 255, 193, 37),

    /** goldenrod2. */
    GOLDENROD_2("goldenrod2", 238, 180, 34),

    /** goldenrod3. */
    GOLDENROD_3("goldenrod3", 205, 155, 29),

    /** goldenrod4. */
    GOLDENROD_4("goldenrod4", 139, 105, 20),

    /** moccasin. */
    MOCCASIN("moccasin", 255, 228, 181),

    /** yellow. */
    YELLOW("yellow", 255, 255, 0),

    /** yellow1. */
    YELLOW_1("yellow1", 255, 255, 0),

    /** yellow2. */
    YELLOW_2("yellow2", 238, 238, 0),

    /** yellow3. */
    YELLOW_3("yellow3", 205, 205, 0),

    /** yellow4. */
    YELLOW_4("yellow4", 139, 139, 0);

    private final String name;
    private final int redComponent;
    private final int greenComponent;
    private final int blueComponent;

    /**
     * Hidden constructor.
     * 
     * @param theName
     *            a human friend color name
     * @param red
     *            the red component of the desired color in range of 0 to 255
     * @param green
     *            the green component of the desired color in range of 0 to 255
     * @param blue
     *            the blue component of the desired color in range of 0 to 255
     */
    private Colors(final String theName, final int red, final int green, final int blue) {
        this.name = theName;
        this.redComponent = red;
        this.greenComponent = green;
        this.blueComponent = blue;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the red component of the desired color in range of 0 to 255
     */
    public int getRed() {
        return redComponent;
    }

    /**
     * @return the green component of the desired color in range of 0 to 255
     */
    public int getGreen() {
        return greenComponent;
    }

    /**
     * @return the blue component of the desired color in range of 0 to 255
     */
    public int getBlue() {
        return blueComponent;
    }
}
