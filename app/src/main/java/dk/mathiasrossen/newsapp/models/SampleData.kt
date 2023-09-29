package dk.mathiasrossen.newsapp.models

object SampleData {
    val articlesSample = listOf(
        Article(
            Article.Source(
                "Meta",
                "Meta"
            ),
            "Meta CEO Mark Zuckerberg looks to digital assistants, smart glasses and AI to help metaverse push - CNBC",
            "Meta is rolling out new AI software in addition to its Quest 3 virtual reality headset and latest smart glasses as it tries to move the world to the metaverse.",
            "https://www.cnbc.com/2023/09/27/meta-ceo-zuckerberg-looks-to-digital-assistants-ai-to-push-metaverse.html",
            "https://image.cnbcfm.com/api/v1/image/107307718-1695846920017-gettyimages-1693652103-AFP_33WW2GR.jpeg?v=1695847029&w=1920&h=1080",
            "2023-09-27T19:52:21Z"
        ),
        Article(
            Article.Source(
                "Hyundai",
                "Hyundai"
            ),
            "In latest recall, Hyundai and Kia ask owners of 3.3 million vehicles to park outside due to risk of fire - CNN",
            "Owners of about 3.3 million Hyundai and Kia cars and SUVs in the United States should avoid parking in or near structures because the vehicles could suddenly catch fire – whether they’re being driven or not, according to an announcement Wednesday from the Nat…",
            "https://www.cnn.com/2023/09/27/business/hyundai-kia-abs-fire-recall/index.html",
            "https://media.cnn.com/api/v1/images/stellar/prod/230927104725-2012-hyundai-accent-file-restricted.jpg?c=16x9&q=w_800,c_fill",
            "2023-09-27T19:52:21Z"
        )
    )
    val articleResponseSample = ArticleResponse("ok", 2, articlesSample)
}
