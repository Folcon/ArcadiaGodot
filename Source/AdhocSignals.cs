using Godot;
using System.Collections.Generic;
using clojure.lang;
using System.Runtime.CompilerServices;

public static class AdhocSignals
{

	public static void AddSignal(Godot.GodotObject o, string name){
		o.AddUserSignal(name);
	}

	public static void AddSignal(Godot.GodotObject o, string name, string[] names, int[] types){
		Godot.Collections.Array arguments = new Godot.Collections.Array();
		for (int i = 0; i < names.Length; i++)
		{
			arguments.Add(new Godot.Collections.Dictionary {{ "name", names[i] }, { "type", types[i] }});
		}
		o.AddUserSignal(name, arguments);
	}

    public static Callable Connect(GodotObject emitter, string signal, IFn fn)
    {
	    var cb = MakeCallable(fn, SignalArgCount(emitter, signal));
		emitter.Connect(signal, cb);
		return cb;
    }
    
    public static bool IsConnected(GodotObject emitter, string signal, Callable cb)
	    => emitter.IsConnected(signal, cb);

    public static void Disconnect(GodotObject emitter, string signal, Callable cb)
	    => emitter.Disconnect(signal, cb);
    
    static int SignalArgCount(GodotObject o, string signal)
    {
      foreach (Godot.Collections.Dictionary sig in o.GetSignalList())
          if (sig["name"].AsString() == signal)
          {
              return sig["args"].AsGodotArray().Count;
          }
      return 0;
    }

    static Callable MakeCallable(IFn fn, int arity)
    {
      switch (arity)
      {
          case 0:  return Callable.From(() => Inv(fn));
          case 1:  return Callable.From((Variant a) => Inv(fn, a));
          case 2:  return Callable.From((Variant a, Variant b) => Inv(fn, a, b));
          case 3:  return Callable.From((Variant a, Variant b, Variant c) => Inv(fn, a, b, c));
          case 4:  return Callable.From((Variant a, Variant b, Variant c, Variant d) => Inv(fn, a, b, c, d));
          default: return Callable.From((Variant a, Variant b, Variant c, Variant d, Variant e) => Inv(fn, a, b, c, d, e));
      }
    }

    static void Inv(IFn fn, params Variant[] a)
    {
      switch (a.Length)
      {
          case 0:  fn.invoke(); break;
          case 1:  fn.invoke(Arcadia.Util.V(a[0])); break;
          case 2:  fn.invoke(Arcadia.Util.V(a[0]), Arcadia.Util.V(a[1])); break;
          case 3:  fn.invoke(Arcadia.Util.V(a[0]), Arcadia.Util.V(a[1]), Arcadia.Util.V(a[2])); break;
          case 4:  fn.invoke(Arcadia.Util.V(a[0]), Arcadia.Util.V(a[1]), Arcadia.Util.V(a[2]), Arcadia.Util.V(a[3])); break;
          default: fn.invoke(Arcadia.Util.V(a[0]), Arcadia.Util.V(a[1]), Arcadia.Util.V(a[2]), Arcadia.Util.V(a[3]), Arcadia.Util.V(a[4])); break;
      }
    }

    public static void Emit(GodotObject o, string name, object[] args)
    {
      o.EmitSignal(name, Arcadia.Util.ObjectToVariantArray(args));
    }

}
