export class Postagem {
    id: number;
    titulo: string;
    texto: string;
    comentarios: Comentario[]
}

export class Comentario {
    id: number;
    texto: string;
}