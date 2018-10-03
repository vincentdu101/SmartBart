import * as React from "react";
import * as d3 from "d3";
import * as topojson from "topojson-client";
import { TransitionGroup, CSSTransition } from "react-transition-group";
import { IMapProps, IMapState } from "../../types/MapTypes";
import { State } from "./State";
import * as stateIds from "../../data/us-states-ids.json";

export class Map extends React.Component<IMapProps, IMapState> {

    private californiaOnly = "California";

    constructor(props: any) {
        super(props);
        
        this.generateMap = this.generateMap.bind(this);
        this.generatePath = this.generatePath.bind(this);

        this.state = {
            states: stateIds,
            maps: null,
            stations: []
        };
    }

    public componentDidMount(): void {
        this.setState({maps: null});
    }

    public componentDidUpdate(): void {

    }

    public componentWillReceiveProps(nextProps: Readonly<any>): void {
        this.setState({maps: nextProps.maps, stations: nextProps.stations});
    }

    private generateCircles(): JSX.Element {
        if (this.state.maps) {
            const data = [[-122.490402, 37.786453], [-122.389809, 37.72728], [-78.917377, 39.757239], [-81.307761, 33.468848]];
            // const data = topojson.feature(maps, maps.objects.counties).features;
            const projection = d3.geoMercator()
                                .scale(960)
                                .center([-95.8, 37.9]);
            return (
                <TransitionGroup component={null}>
                    {data.map((feature: [number, number], i) => {
                        const fill = "steelblue";

                        console.log(feature);
                        return (
                            <CSSTransition
                                key={i}
                                classNames={`state-transition-${i}`}
                                appear={true}
                                timeout={5000}>

                                <g className="circle-container">
                                    <circle
                                        className={`states-circle raw state-transition-circle-${i}`}
                                        r={5}
                                        fill={fill}
                                        stroke="#000000"
                                        strokeWidth={0.5}
                                        transform={'translate(' + projection(feature) + ')'}
                                        opacity={0.75}
                                    />
                                </g>

                            </CSSTransition>
                        );
                    })}
                </TransitionGroup>
            );
        } else {
            return (<div>test</div>);
        }
    }

    private generatePath(geoPath: any, data: any): JSX.Element | undefined {
        const mapType = "US";

        return (
            <TransitionGroup component={null}>
                {data.map((feature: any, i: number) => {
                    // const breaks = this.getChoroplethBreaks();
                    const fill = "#0de298";
                    const path = geoPath(feature);

                    if (this.state.states[feature.id].name === this.californiaOnly) {
                        return (
                            <CSSTransition
                                key={i}
                                classNames={`state-transition-${i}`}
                                appear={true}
                                timeout={5000}
                            >
    
                                <State
                                    mapType={mapType}
                                    stateName={feature.properties.stateName}
                                    stations={this.state.stations}
                                    path={path}
                                    feature={feature}
                                    i={i}
                                    fill={fill}
                                    radius={40}
                                />
    
                            </CSSTransition>
                        );
                    } else {
                        return undefined;
                    }
                })}
            </TransitionGroup>
        );
    }

    public generateMap(): JSX.Element | undefined {
        if (this.state.maps) {
            const maps = this.state.maps;
            const data = topojson.feature(maps, maps.objects.states)["features"];
            return this.generatePath(d3.geoPath(), data);
        } else {
            return (<div>test</div>);
        }
    }

    public render(): JSX.Element {
        return (
            <div className="map-container">
                <svg
                    className={`map US`}
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 400 600"
                >
                    {this.generateMap()}
                    {this.generateCircles()}
                </svg>
            </div>
        );
    }

}