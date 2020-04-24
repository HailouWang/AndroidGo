class SingleInstance {
  SingleInstance._() {
    print("222222");
  }

  SingleInstance(String name){
  }
}
void main(){
  print("11111111111111");

  SingleInstance singleInstance = SingleInstance._();
}
