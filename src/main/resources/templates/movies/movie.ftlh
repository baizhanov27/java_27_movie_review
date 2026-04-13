<#import "../layout.ftlh" as main>
<@main.layout>

    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <#if movie.imageName??>
                    <img src="${movie.imageName}" class="d-block w-100" alt="movie.title">
                <#else>
                    <img src="https://static.vecteezy.com/system/resources/previews/003/586/230/non_2x/no-photo-sign-sticker-with-text-inscription-on-isolated-background-free-vector.jpg"
                         alt="No Image">
                </#if>
                <div class="carousel-caption d-none d-md-block">
                    <h5>${movie.title}</h5>
                    <p>${movie.director}</p>
                    <p>${movie.releaseYear}</p>
                </div>
            </div>
            <div class="carousel-item">
                <#if movie.imageName??>
                    <img src="${movie.imageName}" class="d-block w-100" alt="movie.title">
                <#else>
                    <img src="https://static.vecteezy.com/system/resources/previews/003/586/230/non_2x/no-photo-sign-sticker-with-text-inscription-on-isolated-background-free-vector.jpg"
                         alt="No Image">
                </#if>
                <div class="carousel-caption d-none d-md-block">
                    <h5>${movie.title}</h5>
                    <p>${movie.director}</p>
                    <p>${movie.releaseYear}</p>
                </div>
            </div>
            <div class="carousel-item">
                <#if movie.imageName??>
                    <img src="${movie.imageName}" class="d-block w-100" alt="movie.title">
                <#else>
                    <img src="https://static.vecteezy.com/system/resources/previews/003/586/230/non_2x/no-photo-sign-sticker-with-text-inscription-on-isolated-background-free-vector.jpg"
                         alt="No Image">
                </#if>
                <div class="carousel-caption d-none d-md-block">
                    <h5>${movie.title}</h5>
                    <p>${movie.director}</p>
                    <p>${movie.releaseYear}</p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

</@main.layout>