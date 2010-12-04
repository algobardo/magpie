package magpie

trait category[h <: hom.set] extends precategory[h] {
  def id[A>:h#inf<:h#sup]: h#hom[A,A]
  override def dual : category[hom.set.dual[h]] = category.op(this)
}
  
object category { 
  import equality._
  def duality[h<:hom.set] : category[h] === category[hom.set.dual[hom.set.dual[h]]] = 
     hom.set.duality[h].lift[Nothing,Any,({type λ[x<:hom.set] = category[x]})#λ]

  trait op[h <: hom.set] extends category[hom.set.dual[h]] with precategory.op[h] { 
    def id[A>:h#inf<:h#sup]: h#hom[A,A] = dual.id[A]
  }

  object op { 
    def apply[h<:hom.set](c : category[h]) : op[h] = new op[h] {
      override def dual : category[hom.set.dual[hom.set.dual[h]]] = witness(duality[h])(c)
    }
  }
}
