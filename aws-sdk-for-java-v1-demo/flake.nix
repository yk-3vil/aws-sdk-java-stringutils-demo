{
  description = "AWS SDK for Java v1 - StringUtils.isNullOrEmpty Demo";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs { inherit system; };
        # corretto11: ソースビルド失敗、jdk11: JVMクラッシュ
        # temurin-bin-11 (Eclipse Adoptium プリビルト) を代替使用
        jdk = pkgs.temurin-bin-11;
      in
      {
        devShells.default = pkgs.mkShell {
          buildInputs = [
            jdk
          ];

          shellHook = ''
            export JAVA_HOME="${jdk}"
            echo "AWS SDK for Java v1 Demo - devShell"
            echo "  Java: $(java -version 2>&1 | head -1)"
          '';
        };
      }
    );
}
