package jmaster.io.evnloyalty.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jmaster.io.evnloyalty.model.Post;

public class BlogService {

    public List<Post> findPosts() {
        List<Post> posts = new ArrayList<>();

        posts.add(new Post(1L, "EVN và UBND tỉnh Ninh Bình họp tháo gỡ vướng mắc trong triển khai các dự án điện",
                "Chiều 27/5, tại tỉnh Ninh Bình, lãnh đạo Tập đoàn Điện lực Việt Nam (EVN) có buổi làm việc với lãnh đạo UBND tỉnh Ninh Bình để tháo gỡ khó khăn, vướng mắc trong triển khai các dự án điện trên địa bàn tỉnh.",
                Arrays.asList("https://tdtt.gov.vn/Portals/0/EasyGalleryImages/1/8801/0010323_Chuyen%20doi%20so.jpg.jpeg"),
                "18:56 | 27/05/2022 "));

        posts.add(new Post(2L, "EVN và UBND tỉnh Ninh Bình họp tháo gỡ vướng mắc trong triển khai các dự án điện",
                "Chiều 27/5, tại tỉnh Ninh Bình, lãnh đạo Tập đoàn Điện lực Việt Nam (EVN) có buổi làm việc với lãnh đạo UBND tỉnh Ninh Bình để tháo gỡ khó khăn, vướng mắc trong triển khai các dự án điện trên địa bàn tỉnh.",
                Arrays.asList("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/4/12/1033148/Anh-1-01.jpg"),
                "18:56 | 27/05/2022 "));

        posts.add(new Post(3L, "EVN và UBND tỉnh Ninh Bình họp tháo gỡ vướng mắc trong triển khai các dự án điện",
                "Chiều 27/5, tại tỉnh Ninh Bình, lãnh đạo Tập đoàn Điện lực Việt Nam (EVN) có buổi làm việc với lãnh đạo UBND tỉnh Ninh Bình để tháo gỡ khó khăn, vướng mắc trong triển khai các dự án điện trên địa bàn tỉnh.",
                Arrays.asList("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/4/12/1033148/Anh-1-01.jpg"),
                "18:56 | 27/05/2022 "));

        posts.add(new Post(4L, "EVN và UBND tỉnh Ninh Bình họp tháo gỡ vướng mắc trong triển khai các dự án điện",
                "Chiều 27/5, tại tỉnh Ninh Bình, lãnh đạo Tập đoàn Điện lực Việt Nam (EVN) có buổi làm việc với lãnh đạo UBND tỉnh Ninh Bình để tháo gỡ khó khăn, vướng mắc trong triển khai các dự án điện trên địa bàn tỉnh.",
                Arrays.asList("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/4/12/1033148/Anh-1-01.jpg"),
                "18:56 | 27/05/2022 "));

        posts.add(new Post(5L, "EVN và UBND tỉnh Ninh Bình họp tháo gỡ vướng mắc trong triển khai các dự án điện",
                "Chiều 27/5, tại tỉnh Ninh Bình, lãnh đạo Tập đoàn Điện lực Việt Nam (EVN) có buổi làm việc với lãnh đạo UBND tỉnh Ninh Bình để tháo gỡ khó khăn, vướng mắc trong triển khai các dự án điện trên địa bàn tỉnh.",
                Arrays.asList("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/4/12/1033148/Anh-1-01.jpg"),
                "18:56 | 27/05/2022 "));

        return posts;
    }

}
