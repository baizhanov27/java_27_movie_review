<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="row row-cols-3 row-cols-md-6 row-cols-lg-6">
        <#list movies as movie>
            <div class="card col mx-auto my-2" style="width: 18rem;">
                <#--                <img src="..." class="card-img-top" alt="...">-->
                <div class="card-body">
                    <h5 class="card-title">${movie.title}</h5>
                    <p class="card-text">${movie.director}</p>
                    <p class="card-footer">${movie.releaseYear}</p>
                    <a href="movies/${movie.id}" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
        </#list>
    </div>

</@main.layout>