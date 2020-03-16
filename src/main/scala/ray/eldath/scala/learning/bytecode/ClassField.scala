package ray.eldath.scala.learning.bytecode

class ClassField(val valField: String, var varField: String, field: String, anotherField: String) {
  override def toString = s"val: $valField ++ var: $varField ++ field: $field"
}

class SingleClassField(var field: String)