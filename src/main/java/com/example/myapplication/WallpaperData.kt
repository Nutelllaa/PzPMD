package com.example.myapplication   // <-- REPLACE with your actual package name

// Simple data model for one wallpaper
data class Wallpaper(
    val id: Int,
    val title: String,
    val imageUrl: String
    // If you ever want to use local drawables instead of URLs, you could
    // add something like: val imageRes: Int? = null
)

// This object holds the list of wallpapers
object WallpaperData {

    // ======================= IMPORTANT FOR YOU ==========================
    // HERE is where you customize your wallpapers.
    // To add a new wallpaper, just copy one line and change:
    // - id (must be unique)
    // - title (any text you want)
    // - imageUrl (your own image link)
    // Example:
    // Wallpaper(id = 4, title = "My Custom Wallpaper", imageUrl = "https://..."),
    // ===================================================================
    val wallpapers = listOf(
        Wallpaper(
            id = 1,
            title = "BMW",
            imageUrl = "https://i.postimg.cc/7hGnSy2S/rodan-can-6cq-JPe-TIuls-unsplash.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 2,
            title = "BMW",
            imageUrl = "https://i.postimg.cc/3wxSWVt3/artiom-vallat-f-N5c-Vlpa-Jf-A-unsplash.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 4,
            title = "BMW",
            imageUrl = "https://i.postimg.cc/d3PjRxmb/alex-dorcioman-Mlxqo9b0J4A-unsplash.jpgS" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 5,
            title = "BMW",
            imageUrl = "https://iili.io/fqm35bt.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 6,
            title = "BMW",
            imageUrl = "https://iili.io/fqmVI4e.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 7,
            title = "BMW",
            imageUrl = "https://iili.io/fqmjFPS.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 8,
            title = "BMW",
            imageUrl = "https://iili.io/fqmwlr7.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 9,
            title = "BMW",
            imageUrl = "https://iili.io/fqmOFPp.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 10,
            title = "BMW",
            imageUrl = "https://iili.io/fqmkd4p.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 11,
            title = "BMW",
            imageUrl = "https://iili.io/fBFubuR.png" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 12,
            title = "BMW",
            imageUrl = "https://iili.io/fBF7jNj.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 13,
            title = "BMW",
            imageUrl = "https://iili.io/fBFlRql.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 14,
            title = "BMW",
            imageUrl = "https://iili.io/fBF0Wjs.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 15,
            title = "BMW",
            imageUrl = "https://iili.io/fBFwGfe.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 16,
            title = "BMW",
            imageUrl = "https://iili.io/fBFw1s9.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 17,
            title = "BMW",
            imageUrl = "https://iili.io/fBFj6o7.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 18,
            title = "BMW",
            imageUrl = "https://iili.io/fBFjUAl.png" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 19,
            title = "BMW",
            imageUrl = "https://iili.io/fBFjwMX.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 20,
            title = "BMW",
            imageUrl = "https://iili.io/fBFjdv4.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 21,
            title = "BMW",
            imageUrl = "https://iili.io/fBF0Wjs.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 22,
            title = "BMW",
            imageUrl = "https://iili.io/fBFifte.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 23,
            title = "BMW",
            imageUrl = "https://iili.io/fBFDuHv.png" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 24,
            title = "BMW",
            imageUrl = "https://iili.io/fBFD5DN.png" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 25,
            title = "BMW",
            imageUrl = "https://iili.io/fBFDYxI.png" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 26,
            title = "BMW",
            imageUrl = "https://iili.io/fBFDaVt.jpg" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 27,
            title = "BMW",
            imageUrl = "https://iili.io/fBFDLqF.png" // <--- PASTE YOUR URL HERE
        ),
        Wallpaper(
            id = 28,
            title = "BMW",
            imageUrl = "https://iili.io/fBFDQ0g.png" // <--- PASTE YOUR URL HERE
        ), Wallpaper(
            id = 29,
            title = "BMW",
            imageUrl = "https://iili.io/fBFbzL7.jpg" // <--- PASTE YOUR URL HERE
        ), Wallpaper(
            id = 30,
            title = "BMW",
            imageUrl = "https://iili.io/fBfPYaR.png" // <--- PASTE YOUR URL HERE
        ),
            Wallpaper(
            id = 31,
            title = "BMW",
            imageUrl = "https://iili.io/fBfPK4j.jpg" // <--- PASTE YOUR URL HERE
        )

    )
}
